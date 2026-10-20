# Static site and book: implementation plan

Turn this repo into a static site at **https://xingyu-long.github.io/LeetCode-Solutions/**, where you browse solutions by category. The same build also produces a downloadable book (PDF and EPUB).

Mockups (private canvas, owner's account): https://claude.ai/artifact/QcZJuc6SPF4RcvVUsznJz9

- **Site:** board *1 · Docs*, plus the Java/Python side-by-side switch from board *5 · Split reader*.
- **Book:** board *Book export*, in the style of board *3 · Editorial*.

Decisions already made. Don't reopen these:
- The single source of truth is `Leetcode/src/com/leetcode/`. Nothing else is published.
- Unnumbered files (helpers, HackerRank pieces, sorting demos) are left off the site.
- Fetch official titles and difficulty once into a committed `titles.json`.
- Delete `organized_solutions/`, the Sphinx `main.py`, `generate_table.py` and the README tables.
- Leave `templates/` (the old folder at the repo root) and `submit.py` untouched.

---

## 1. Content rules (how files become pages)

These rules also cover **future additions**. Add a correctly named file in the right folder, push, and it appears on the site and in the book. Nothing else needs editing.

| What you add | Result |
|---|---|
| `Leetcode/src/com/leetcode/<category>/_<num>_<name>.<ext>` | A problem in that category |
| `…/<category>/<subtopic>/_<num>_<name>.<ext>` | A problem in that category, listed under the subtopic |
| `Leetcode/src/com/leetcode/_<num>_<name>.<ext>` (directly in the root) | A problem in **Others** |
| A new top-level folder | A new category in the sidebar automatically |
| A new subfolder | A new subtopic section automatically |
| A second file with the same number in the same category (another language) | Another language tab on the same problem page |
| Anything outside `Leetcode/src/com/leetcode/` | Ignored |
| A file that doesn't match the naming rule | Skipped, and the build prints a warning listing it |

Details:
- **Filename regex:** `^_(\d+)_(.+)\.([A-Za-z0-9]+)$`. Both CamelCase (`_56_MergeIntervals.java`) and snake_case (`_56_merge_intervals.py`) are valid.
- **Languages** are picked by extension through one map in the builder: `.java` Java, `.py` Python, `.cpp`/`.cc` C++, `.c` C, `.js` JavaScript, `.ts` TypeScript, `.go` Go, `.rs` Rust, `.kt` Kotlin, `.swift` Swift, `.sql` SQL, `.sh` Bash. Unknown extensions are skipped with a warning. Tabs are ordered Java, Python, then alphabetically.
- **Subtopic:** the folder path between the category and the file. Today that's at most one level, but deeper paths should join with " / ".
- **Several files in the same language** for one problem (rare): show the tab as "Java (2)" and stack both files in it, each with its own filename caption.
- **A problem in several categories** (30 today, e.g. 55 is in `array` and `greedy`): it gets a page in each category. Each page shows "Also in …" links. The canonical URL (`<link rel="canonical">`) is the category that comes first alphabetically.
- **Near-miss filenames:** the four that existed (`46_permutation.py`, `547_number_of_provinces.py`, `_804UniqueMorseCodeWords.java`, `1762._buildings_with_an_ocean_view.py`) have already been renamed to the `_<num>_<name>` form, so a clean build should print **no** naming warnings. Problem 46 now has two Python files in `backtracking/`, so it shows a "Python (2)" tab.
- **Expected totals right now:** about 1,075 distinct problems in 25 categories (24 folders plus Others). 922 problems have Java and 454 have Python. Print these totals at the end of each build.

### Titles and difficulty
- `site/data/titles.json` is committed and maps each number to `{"title", "slug", "difficulty"}` for **every** LeetCode problem.
- `site/fetch_titles.py` fills it. Use LeetCode's public problem list `https://leetcode.com/api/problems/all/`; if that endpoint fails, fall back to the GraphQL `problemsetQuestionList` query. Run it once locally and commit the result.
- When the builder meets a number missing from `titles.json`, it falls back to a title built from the filename and prints a warning. CI runs `fetch_titles.py --missing-only` before building. That step must not fail the build if LeetCode can't be reached.
- **Title fallback from filenames:** split CamelCase and snake_case, then title-case the words. Use the CamelCase file when both exist.
- The LeetCode link is `https://leetcode.com/problems/<slug>/`. Leave it out when there's no slug.

### Category names and order
- A small display-name map: `stack_priority_queue` → "Stack and queue", `bfs_and_dfs` → "BFS and DFS", `linkedList` → "Linked list", `ood` → "OOD", `dynamic_programming` → "Dynamic programming", `range_sum` → "Range sum", `bit_manipulation` → "Bit manipulation", `topological_sort` → "Topological sort", `two_pointer` → "Two pointer".
- Anything not in the map is humanized automatically (split on `_` and camelCase, sentence case). That's how new categories work.
- Subtopic names follow the same rule (`sub_array` → "Sub-array", `twoPointer` → "Two pointer", `monoStack` → "Mono stack").
- **Sidebar order:** by problem count, largest first, with **Others always last**. Inside a category, problems with no subtopic come first under "General", then subtopics alphabetically. Within each group, sort by problem number.
- **Skip the `common/` folder** (`TreeNode.java` and the like). It has no numbered files anyway.

---

## 2. Site

### Layout
- **Build tool:** `site/build.py`, plain Python with Jinja2 templates in `site/templates/` and static files in `site/static/`.
- **Output:** `_site/`, which is git-ignored.
- **Links:** every link is **relative**, so the site works under the `/LeetCode-Solutions/` path and also when opened from disk.

```
site/
  build.py           # discover, render site and book
  fetch_titles.py
  data/titles.json
  templates/         # base.html, home.html, category.html, problem.html, book.html
  static/            # site.css, site.js, fonts/
tests/test_discovery.py
```

### URLs
- `index.html` is the home page.
- `<category>/index.html` lists a category's problems.
- `<category>/<num>-<slug>/index.html` is a problem page. Use a slug from the filename when there's no official slug.
- `search-index.json` holds `[{n, t, c, u}]` (number, title, category, URL) for the header search.
- `book/leetcode-solutions.pdf` and `book/leetcode-solutions.epub` are the book downloads.

### Design (board 1 · Docs)
- **Fonts:** Inter (UI) and JetBrains Mono (code and numbers). Self-host them in `site/static/fonts/`; both are OFL licensed. No CDN.
- **Colors:**
  - Text `#1f2328`; muted `#59636e`; faint `#8b949e`.
  - Borders `#e6e8eb`; subtle background `#f6f7f9`.
  - Accent `#0b57d0`, with an accent tint at 8% opacity for the active nav item.
  - Difficulty pills: Easy (bg `#e6f4ea`, text `#1a7f37`), Medium (bg `#fff4e0`, text `#8a5300`), Hard (bg `#fde8e8`, text `#b42318`).
- **Header** (60px): logo mark and "LeetCode Solutions", a search box (press `/` to focus, filters `search-index.json` by number or title, top 8 results in a dropdown), and links for "Download book" and "GitHub".
- **Left sidebar** (248px):
  - The categories with their counts.
  - The active category expands to show its subtopics as anchor links.
  - Under 900px wide, the sidebar collapses behind a menu button.
- **Category page:**
  - Title, problem count and subtopic count.
  - A filter input for this category.
  - Subtopic chips that jump to their sections.
  - Problem rows: number, title, difficulty pill and language badges.
- **Problem page:**
  - Breadcrumb (Category / Subtopic).
  - H1 "56. Merge Intervals", a difficulty pill and "Also in …" pills.
  - Buttons: "Open on LeetCode ↗" and "View source", which opens the file on GitHub at `blob/main/<path>`.
  - A code card with a tab bar (one tab per language), the file path, a copy button and **a view switch: Tabs | Side by side** (from board 5).
  - **Side by side** puts the languages in equal columns. It's only offered when there are 2+ languages and the window is at least 1100px wide. Remember the choice in `localStorage`, wrapped in try/catch.
  - Below the card: previous and next links within the category.
  - Right rail (hidden under 1200px): "More in <subtopic>".
- **Code:**
  - Highlighted at build time with Pygments. Use a light GitHub-like style, with CSS classes rather than inline styles.
  - Line numbers on.
  - Show files in full, keeping the original comments, including the Chinese notes. Make sure the output is UTF-8.
- **Without JavaScript:** pages work fully, and all language blocks are simply visible one after another. JS adds tabs, side by side, search, filtering and copy.
- **Accessibility:** real `<button>` and `<a>` elements, focus outlines, `aria-selected` on tabs, and 4.5:1 contrast.

---

## 3. Book (boards *Book export* and *3 · Editorial*)

- **Content:** the same model rendered into `site/templates/book.html`, a single HTML file with print CSS.
- **Order:**
  1. A cover: "LeetCode Solutions", the subtitle "1,075 problems in Java and Python, filed by technique" (using the live count), the author "Xingyu Long" and the edition month.
  2. The full table of contents.
  3. One chapter per category, in sidebar order with Others last. Each chapter opens on a page with its own contents grouped by subtopic.
  4. Each problem starts on a new page: title, difficulty and LeetCode URL, then every language's code in order.
- **Style:**
  - Fonts: Newsreader (serif, headings and body) and IBM Plex Mono (code and labels). Self-host them (OFL).
  - Colors: ink `#16213a`, accent `#c2410c` for the cover background and section labels, code background `#f6f7f9`.
  - Page size A4.
  - Running header: chapter · subtopic on the left, the LeetCode URL on the right. Page numbers in the footer.
  - Contents entries get real page numbers through `target-counter()`.
  - Code is 8.5–9pt; long lines wrap (`white-space: pre-wrap`) and blocks can break across pages.
- **PDF:** WeasyPrint renders `book.html` to `_site/book/leetcode-solutions.pdf`. Also produce one PDF per chapter at `_site/book/chapters/<category>.pdf`, because the full book will run past 1,500 pages. Link all of them from a small "Book" section on the home page.
- **EPUB:** pandoc converts generated Markdown (one file per chapter) to `_site/book/leetcode-solutions.epub`, with cover metadata and a table of contents.
- **Command-line flags:** `--no-book` builds just the site, for fast local runs. `--book-only` skips the site.

---

## 4. CI (`.github/workflows/docs.yml`, replace the whole file)

- Trigger on pushes to `main` plus `workflow_dispatch`.
- Use `ubuntu-latest`, `actions/checkout@v4` and `astral-sh/setup-uv`, then run `uv sync`.
- `apt-get install pandoc libpango-1.0-0 libpangoft2-1.0-0` for WeasyPrint and pandoc.
- `uv run python site/fetch_titles.py --missing-only || true`
- `uv run python site/build.py`
- Deploy with `actions/configure-pages`, `actions/upload-pages-artifact` (path `_site`) and `actions/deploy-pages`.
- Permissions: `pages: write`, `id-token: write`.
- Concurrency group `pages`.
- **Manual step for the owner:** repo Settings → Pages → Source: "GitHub Actions".

---

## 5. Cleanup

- Delete `organized_solutions/`, `main.py` (Sphinx) and `generate_table.py`.
- **`pyproject.toml`:**
  - Remove `sphinx`, `tqdm` and `pytablewriter`.
  - Add `jinja2`, `pygments` and `weasyprint`.
  - Move `ruff` and `pytest` into a `[dependency-groups] dev` group.
  - Keep `gitpython` (`submit.py`) and `sortedcontainers`.
  - Remove the hatch `leetcode_solutions` build targets and `[build-system]`, since this isn't a package. Set `[tool.uv] package = false`.
  - Run `uv lock`.
- **`.gitignore`:**
  - Remove the Sphinx `build/*` and `source/*` entries and the duplicate `*.iml`.
  - Add `_site/`.
  - Keep `*.json` ignored but un-ignore `site/data/titles.json` with `!site/data/titles.json`.
- **README.md:** replace it with a short intro, a link to the site and the book, the naming rule from section 1, and local build commands.

---

## 6. Tests and verification

- `tests/test_discovery.py` covers:
  - The filename regex, with good names and each known near miss.
  - Grouping by number across languages.
  - A root file going to Others.
  - Subtopic detection.
  - Category name humanizing.
  - A problem in two categories.
  - Missing-title fallback.
  - Use small temporary folders, not the real tree.
- **Local checks:**
  ```bash
  uv run pytest
  uv run python site/build.py --no-book
  uv run python -m http.server -d _site 8000
  ```
  - Totals print as about 1,075 problems and 25 categories, and there are no naming warnings.
  - Check `/array/`, `/array/56-merge-intervals/` (Java and Python, side by side works), `/others/` and a problem in two categories (55).
  - Check that the search finds "merge" and "56".
  - Check at phone width.
- Run the full build with the book at least once, and open the PDF to check the cover, the table of contents page numbers and one problem page.
- Don't commit or push until the owner has reviewed the site locally.
