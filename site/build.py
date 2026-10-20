"""Build the static site (and optionally a book) from Leetcode/src/com/leetcode/.

    uv run python site/build.py              # site only, into _site/ (what CI deploys)
    uv run python site/build.py --book       # site, plus PDF and EPUB in _book/
    uv run python site/build.py --book-only  # just the book, in _book/
"""

from __future__ import annotations

import argparse
import datetime
import html
import json
import os
import re
import shutil
import subprocess
import sys
from collections import defaultdict
from dataclasses import dataclass, field
from pathlib import Path

from jinja2 import Environment, FileSystemLoader, select_autoescape
from pygments import highlight
from pygments.formatters import HtmlFormatter
from pygments.lexers import get_lexer_by_name
from pygments.style import Style
from pygments.token import (
    Comment,
    Error,
    Generic,
    Keyword,
    Name,
    Number,
    Operator,
    String,
    Token,
)

HERE = Path(__file__).resolve().parent
REPO = HERE.parent
SRC = REPO / "Leetcode" / "src" / "com" / "leetcode"
OUT = REPO / "_site"
BOOK_OUT = REPO / "_book"
TITLES_PATH = HERE / "data" / "titles.json"

GITHUB = "https://github.com/xingyu-long/LeetCode-Solutions"
SITE_URL = "https://xingyu-long.github.io/LeetCode-Solutions/"
AUTHOR = "Xingyu Long"

FILENAME_RE = re.compile(r"^_(\d+)_(.+)\.([A-Za-z0-9]+)$")
OTHERS = "others"
SKIP_DIRS = {"common"}

# extension -> (display name, pygments lexer)
LANGS = {
    "java": ("Java", "java"),
    "py": ("Python", "python"),
    "cpp": ("C++", "cpp"),
    "cc": ("C++", "cpp"),
    "c": ("C", "c"),
    "js": ("JavaScript", "javascript"),
    "ts": ("TypeScript", "typescript"),
    "go": ("Go", "go"),
    "rs": ("Rust", "rust"),
    "kt": ("Kotlin", "kotlin"),
    "swift": ("Swift", "swift"),
    "sql": ("SQL", "sql"),
    "sh": ("Bash", "bash"),
}
LANG_ORDER = ["Java", "Python"]

CATEGORY_NAMES = {
    "stack_priority_queue": "Stack and queue",
    "bfs_and_dfs": "BFS and DFS",
    "linkedList": "Linked list",
    "ood": "OOD",
    "dynamic_programming": "Dynamic programming",
    "range_sum": "Range sum",
    "bit_manipulation": "Bit manipulation",
    "topological_sort": "Topological sort",
    "two_pointer": "Two pointer",
    OTHERS: "Others",
}
SUBTOPIC_NAMES = {"sub_array": "Sub-array"}

KNOWN_WORDS = {"bfs", "dfs", "ood", "sql"}


# --------------------------------------------------------------------- naming
def split_words(name: str) -> list[str]:
    name = re.sub(r"([a-z0-9])([A-Z])", r"\1 \2", name)
    name = re.sub(r"([A-Z]+)([A-Z][a-z])", r"\1 \2", name)
    return [w for w in re.split(r"[\s_\-.]+", name) if w]


def humanize(name: str, overrides: dict[str, str] | None = None) -> str:
    """Folder name -> display name, sentence case."""
    if overrides and name in overrides:
        return overrides[name]
    words = [w.upper() if w.lower() in KNOWN_WORDS else w.lower() for w in split_words(name)]
    if not words:
        return name
    if not words[0].isupper():
        words[0] = words[0].capitalize()
    return " ".join(words)


def category_name(key: str) -> str:
    return humanize(key, CATEGORY_NAMES)


def subtopic_name(path: str) -> str:
    return " / ".join(humanize(p, SUBTOPIC_NAMES) for p in path.split("/")) if path else ""


def slugify(s: str) -> str:
    s = "-".join(w.lower() for w in split_words(s))
    return re.sub(r"[^a-z0-9-]+", "", s).strip("-") or "x"


def title_from_filename(stem: str) -> str:
    """'MergeIntervals' / 'merge_intervals' -> 'Merge Intervals'."""
    return " ".join(w[:1].upper() + w[1:] for w in split_words(stem))


def parse_filename(name: str):
    m = FILENAME_RE.match(name)
    return (int(m.group(1)), m.group(2), m.group(3).lower()) if m else None


# ---------------------------------------------------------------------- model
@dataclass
class SourceFile:
    path: Path  # absolute
    rel: str  # relative to repo root, posix
    lang: str
    lexer: str
    stem: str
    code: str = ""
    html: str = ""


@dataclass
class Problem:
    number: int
    category: str  # folder key
    subtopic: str = ""  # raw path, '' = General
    files: list[SourceFile] = field(default_factory=list)
    title: str = ""
    slug: str = ""  # url slug (no number)
    lc_slug: str = ""
    difficulty: str = ""
    also_in: list["Problem"] = field(default_factory=list)
    canonical: bool = True

    @property
    def cat_slug(self):
        return slugify(self.category)

    @property
    def dir(self):
        return f"{self.cat_slug}/{self.number}-{self.slug}"

    @property
    def lc_url(self):
        return f"https://leetcode.com/problems/{self.lc_slug}/" if self.lc_slug else ""

    @property
    def sub_name(self):
        return subtopic_name(self.subtopic)

    def tabs(self):
        """[(lang, [SourceFile])] ordered Java, Python, then alphabetical."""
        by = defaultdict(list)
        for f in self.files:
            by[f.lang].append(f)
        order = [l for l in LANG_ORDER if l in by] + sorted(l for l in by if l not in LANG_ORDER)
        return [(l, sorted(by[l], key=lambda f: f.rel)) for l in order]

    def lang_names(self):
        return [l for l, _ in self.tabs()]


@dataclass
class Category:
    key: str
    problems: list[Problem] = field(default_factory=list)

    @property
    def slug(self):
        return slugify(self.key)

    @property
    def name(self):
        return category_name(self.key)

    def groups(self):
        """[(subtopic raw, display name, [Problem])]: General first, then alphabetical."""
        by = defaultdict(list)
        for p in self.problems:
            by[p.subtopic].append(p)
        keys = sorted(by, key=lambda k: (k != "", subtopic_name(k).lower()))
        return [(k, subtopic_name(k) or "General", sorted(by[k], key=lambda p: p.number)) for k in keys]


def discover(src: Path = SRC, repo: Path | None = None, warn=print):
    """Walk the source tree. Returns {(category, number): Problem}."""
    repo = repo or src
    problems: dict[tuple[str, int], Problem] = {}
    for path in sorted(p for p in src.rglob("*") if p.is_file()):
        rel_parts = path.relative_to(src).parts
        if rel_parts[0] in SKIP_DIRS and len(rel_parts) > 1:
            continue
        if path.name.startswith(".") or "__pycache__" in rel_parts:
            continue
        parsed = parse_filename(path.name)
        try:
            rel = path.relative_to(repo).as_posix()
        except ValueError:
            rel = path.as_posix()
        if not parsed:
            # unnumbered helpers are left off silently; a digit in the name suggests a near miss
            if path.suffix.lower().lstrip(".") in LANGS and re.search(r"\d", path.stem):
                warn(f"WARNING: naming rule not matched, skipped: {rel}")
            continue
        number, stem, ext = parsed
        if ext not in LANGS:
            warn(f"WARNING: unknown extension, skipped: {rel}")
            continue
        lang, lexer = LANGS[ext]
        if len(rel_parts) == 1:
            category, sub = OTHERS, ""
        else:
            category = rel_parts[0]
            sub = "/".join(rel_parts[1:-1])
        prob = problems.setdefault((category, number), Problem(number, category, sub))
        prob.files.append(SourceFile(path, rel, lang, lexer, stem))
    for prob in problems.values():
        prob.subtopic = subtopic_path(prob, src)
    return problems


def subtopic_path(prob: Problem, src: Path) -> str:
    subs = []
    for f in prob.files:
        parts = f.path.relative_to(src).parts
        subs.append("/".join(parts[1:-1]) if len(parts) > 1 else "")
    return sorted(subs)[0]


def apply_titles(problems, titles: dict, warn=print):
    """Fill title / slug / difficulty; fall back to filenames with a warning."""
    for prob in problems.values():
        t = titles.get(str(prob.number))
        if t:
            prob.title = t["title"]
            prob.lc_slug = t.get("slug", "")
            prob.difficulty = t.get("difficulty", "")
            prob.slug = prob.lc_slug or slugify(prob.title)
        else:
            camel = [f for f in prob.files if re.search(r"[A-Z]", f.stem)]
            stem = (camel or prob.files)[0].stem
            prob.title = title_from_filename(stem)
            prob.slug = slugify(prob.title)
            warn(f"WARNING: no official title for {prob.number}, using {prob.title!r}")


def link_categories(problems):
    """Group into categories, mark canonical and also-in relations."""
    by_num = defaultdict(list)
    for p in problems.values():
        by_num[p.number].append(p)
    for group in by_num.values():
        group.sort(key=lambda p: p.cat_slug)
        for p in group:
            p.canonical = p is group[0]
            p.also_in = [q for q in group if q is not p]
    cats = defaultdict(list)
    for p in problems.values():
        cats[p.category].append(p)
    out = [Category(k, sorted(v, key=lambda p: p.number)) for k, v in cats.items()]
    out.sort(key=lambda c: (c.key == OTHERS, -len(c.problems), c.name.lower()))
    return out


# ------------------------------------------------------------------ highlight
class GithubLight(Style):
    background_color = "#f6f7f9"
    default_style = ""
    styles = {
        Comment: "italic #59636e",
        Keyword: "#cf222e",
        Keyword.Type: "#0550ae",
        Operator: "#cf222e",
        Name.Class: "bold #953800",
        Name.Function: "#8250df",
        Name.Decorator: "#8250df",
        Name.Builtin: "#0550ae",
        Name.Constant: "#0550ae",
        Number: "#0550ae",
        String: "#0a3069",
        Generic.Deleted: "#82071e",
        Generic.Inserted: "#116329",
        Error: "#cf222e",
        Token: "",
    }


def read_text(path: Path) -> str:
    data = path.read_bytes()
    for enc in ("utf-8", "gb18030", "latin-1"):
        try:
            return data.decode(enc).replace("\r\n", "\n").replace("\r", "\n")
        except UnicodeDecodeError:
            continue
    return data.decode("utf-8", "replace")


def highlight_all(problems, with_numbers: bool):
    fmt = HtmlFormatter(style=GithubLight, cssclass="highlight", linenos="table" if with_numbers else False, nowrap=False)
    for prob in problems.values():
        for f in prob.files:
            if f.html:
                continue
            f.code = read_text(f.path)
            f.html = highlight(f.code, get_lexer_by_name(f.lexer), fmt)
    return fmt


def pygments_css(prefix=".highlight"):
    return HtmlFormatter(style=GithubLight).get_style_defs(prefix)


# --------------------------------------------------------------------- render
def make_env():
    env = Environment(
        loader=FileSystemLoader(HERE / "templates"),
        autoescape=select_autoescape(["html"]),
        trim_blocks=True,
        lstrip_blocks=True,
    )
    env.filters["comma"] = lambda n: f"{n:,}"
    env.globals["anchor"] = subtopic_anchor
    env.globals["cat_name"] = category_name
    return env


def write(path: Path, text: str):
    path.parent.mkdir(parents=True, exist_ok=True)
    path.write_text(text, encoding="utf-8")


def sidebar_data(categories):
    return [{"slug": c.slug, "name": c.name, "count": len(c.problems), "key": c.key} for c in categories]


def build_site(categories, problems, stats):
    env = make_env()
    side = sidebar_data(categories)
    common = {"sidebar": side, "github": GITHUB, "stats": stats, "year": datetime.date.today().year}
    static_out = OUT / "static"
    shutil.copytree(HERE / "static", static_out, dirs_exist_ok=True)
    write(static_out / "pygments.css", pygments_css())

    # home
    write(
        OUT / "index.html",
        env.get_template("home.html").render(
            root="", page="home", title="LeetCode Solutions", categories=side, **common
        ),
    )

    search = []
    for cat in categories:
        groups = cat.groups()
        write(
            OUT / cat.slug / "index.html",
            env.get_template("category.html").render(
                root="../", page="category", title=cat.name, cat=cat, groups=groups,
                active=cat.slug, subtopics=[(subtopic_anchor(k), n) for k, n, _ in groups if k], **common,
            ),
        )
        for i, prob in enumerate(cat.problems):
            prev_p = cat.problems[i - 1] if i > 0 else None
            next_p = cat.problems[i + 1] if i + 1 < len(cat.problems) else None
            same_sub = [q for q in cat.problems if q.subtopic == prob.subtopic and q is not prob]
            canon = prob if prob.canonical else next(q for q in prob.also_in if q.canonical)
            canonical_url = SITE_URL + canon.dir + "/"
            write(
                OUT / prob.dir / "index.html",
                env.get_template("problem.html").render(
                    root="../../", page="problem", title=f"{prob.number}. {prob.title}", prob=prob, cat=cat,
                    active=cat.slug, subtopics=[(subtopic_anchor(k), n) for k, n, _ in groups if k], tabs=prob.tabs(), prev=prev_p, next=next_p, more=same_sub[:12],
                    canonical_url=canonical_url, site_url=SITE_URL, github_blob=f"{GITHUB}/blob/main/",
                    **common,
                ),
            )
            if prob.canonical:
                search.append({"n": prob.number, "t": prob.title, "c": cat.name, "u": prob.dir + "/"})
    search.sort(key=lambda e: e["n"])
    write(OUT / "search-index.json", json.dumps(search, ensure_ascii=False, separators=(",", ":")))
    write(OUT / ".nojekyll", "")


def subtopic_anchor(raw: str) -> str:
    return "sub-" + slugify(raw.replace("/", " "))


# ----------------------------------------------------------------------- book
def book_context(categories, chapters_filter=None):
    cats = [c for c in categories if chapters_filter is None or c.key == chapters_filter]
    out = []
    for c in cats:
        out.append(
            {
                "slug": c.slug,
                "name": c.name,
                "count": len(c.problems),
                "groups": [
                    {
                        "id": f"{c.slug}-{subtopic_anchor(k)}" if k else f"{c.slug}-general",
                        "name": n,
                        "problems": ps,
                    }
                    for k, n, ps in c.groups()
                ],
            }
        )
    return out


def render_book_html(env, categories, stats, only=None):
    chapters = book_context(categories, only)
    return env.get_template("book.html").render(
        chapters=chapters, stats=stats, author=AUTHOR, edition=datetime.date.today().strftime("%B %Y"),
        site_url=SITE_URL, only=only, fonts=(HERE / "static" / "fonts").as_uri(),
        pygments=pygments_css(), book_title="LeetCode Solutions",
        subtitle=f"{stats['problems']:,} problems in Java and Python, filed by technique",
    )


def build_book(categories, problems, stats):
    # Homebrew keeps pango outside the default dlopen path on macOS
    if sys.platform == "darwin" and Path("/opt/homebrew/lib").exists():
        os.environ.setdefault("DYLD_FALLBACK_LIBRARY_PATH", "/opt/homebrew/lib")
    from weasyprint import HTML

    env = make_env()
    book_dir = BOOK_OUT
    (book_dir / "chapters").mkdir(parents=True, exist_ok=True)
    html_text = render_book_html(env, categories, stats)
    write(book_dir / "_book.html", html_text)
    print("Rendering full PDF (this takes a while)...", flush=True)
    HTML(string=html_text, base_url=str(HERE)).write_pdf(book_dir / "leetcode-solutions.pdf")
    for c in categories:
        print(f"  chapter PDF: {c.slug}", flush=True)
        t = render_book_html(env, categories, stats, only=c.key)
        HTML(string=t, base_url=str(HERE)).write_pdf(book_dir / "chapters" / f"{c.slug}.pdf")
    (book_dir / "_book.html").unlink()
    build_epub(categories, stats, book_dir)


def md_escape_title(s):
    return s.replace("[", r"\[").replace("]", r"\]")


def build_epub(categories, stats, book_dir: Path):
    pandoc = shutil.which("pandoc")
    if not pandoc:
        print("WARNING: pandoc not found, EPUB skipped")
        return
    md_dir = book_dir / "_md"
    shutil.rmtree(md_dir, ignore_errors=True)
    md_dir.mkdir(parents=True)
    files = []
    for i, c in enumerate(categories, 1):
        lines = [f"# {c.name} {{#{c.slug}}}", ""]
        for _, name, probs in c.groups():
            lines += [f"## {name}", ""]
            for p in probs:
                lines += [f"### {p.number}. {md_escape_title(p.title)}", ""]
                meta = " · ".join(x for x in [p.difficulty, p.lc_url] if x)
                if meta:
                    lines += [meta, ""]
                for lang, fs in p.tabs():
                    for f in fs:
                        lines += [f"**{lang}** — `{Path(f.rel).name}`", "", f"```{f.lexer}", f.code.rstrip("\n"), "```", ""]
        fp = md_dir / f"{i:02d}-{c.slug}.md"
        fp.write_text("\n".join(lines), encoding="utf-8")
        files.append(str(fp))
    meta = md_dir / "metadata.yaml"
    meta.write_text(
        "---\n"
        f'title: "LeetCode Solutions"\nsubtitle: "{stats["problems"]:,} problems in Java and Python, filed by technique"\n'
        f'author: "{AUTHOR}"\nlang: en\ndate: "{datetime.date.today().isoformat()}"\n---\n',
        encoding="utf-8",
    )
    cmd = [pandoc, str(meta), *files, "-o", str(book_dir / "leetcode-solutions.epub"), "--toc", "--toc-depth=2",
           "--highlight-style=pygments", "--split-level=1", "--metadata", "toc-title=Contents"]
    cover = HERE / "static" / "cover.png"
    if cover.exists():
        cmd += ["--epub-cover-image", str(cover)]
    print("Building EPUB...", flush=True)
    r = subprocess.run(cmd, capture_output=True, text=True)
    if r.returncode:
        print("WARNING: pandoc failed:", r.stderr[:500])
    shutil.rmtree(md_dir, ignore_errors=True)


# ----------------------------------------------------------------------- main
def compute_stats(categories, problems):
    nums = {p.number for p in problems.values()}
    return {
        "problems": len(nums),
        "categories": len(categories),
        "java": len({p.number for p in problems.values() if "Java" in p.lang_names()}),
        "python": len({p.number for p in problems.values() if "Python" in p.lang_names()}),
        "pages": len(problems),
    }


def main(argv=None):
    ap = argparse.ArgumentParser(description="Build the static site, and optionally the PDF/EPUB book.")
    ap.add_argument("--book", action="store_true", help="also build the book into _book/ (needs pandoc and pango)")
    ap.add_argument("--book-only", action="store_true", help="build only the book, leave _site/ alone")
    args = ap.parse_args(argv)

    titles = json.loads(TITLES_PATH.read_text(encoding="utf-8")) if TITLES_PATH.exists() else {}
    problems = discover(SRC, REPO)
    apply_titles(problems, titles)
    categories = link_categories(problems)
    stats = compute_stats(categories, problems)
    highlight_all(problems, with_numbers=not args.book_only)

    if not args.book_only:
        shutil.rmtree(OUT, ignore_errors=True)
        build_site(categories, problems, stats)
    if args.book or args.book_only:
        shutil.rmtree(BOOK_OUT, ignore_errors=True)
        # book pages use plain highlighting without the line-number table
        for p in problems.values():
            for f in p.files:
                f.html = ""
        highlight_all(problems, with_numbers=False)
        build_book(categories, problems, stats)

    print(
        f"{stats['problems']:,} problems in {stats['categories']} categories "
        f"({stats['java']} with Java, {stats['python']} with Python); {stats['pages']:,} pages"
    )
    return 0


if __name__ == "__main__":
    sys.exit(main())
