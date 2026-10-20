# LeetCode Solutions

My LeetCode solutions in Java and Python, filed by technique.

- **Site:** https://xingyu-long.github.io/LeetCode-Solutions/

## Adding a solution

The only source is `Leetcode/src/com/leetcode/`. Add a correctly named file in the right folder, push, and it shows up on the site.

| What you add | Result |
|---|---|
| `Leetcode/src/com/leetcode/<category>/_<num>_<name>.<ext>` | A problem in that category |
| `…/<category>/<subtopic>/_<num>_<name>.<ext>` | The same, listed under the subtopic |
| `Leetcode/src/com/leetcode/_<num>_<name>.<ext>` | A problem in **Others** |
| A new top-level folder | A new category |
| A second file with the same number (another language) | Another language tab |

The filename must match `^_(\d+)_(.+)\.([A-Za-z0-9]+)$`; both `_56_MergeIntervals.java` and `_56_merge_intervals.py` work. Files that don't match are skipped (the build prints a warning when the name has a number in it). Supported extensions: java, py, cpp, cc, c, js, ts, go, rs, kt, swift, sql, sh.

## Building locally

```bash
uv sync
uv run pytest
uv run python site/build.py                # site only, into _site/
uv run python -m http.server -d _site 8000
```

### Optional: build the book

The PDF and EPUB are not part of the website or the deploy. Build them locally when you want them (needs `pandoc` and `pango`, e.g. `brew install pandoc pango`):

```bash
uv run python site/build.py --book         # site, plus the book in _book/
uv run python site/build.py --book-only    # only the book, leaves _site/ alone
```

The full PDF runs well past 1,500 pages and takes several minutes. Output goes to `_book/`: `leetcode-solutions.pdf`, `leetcode-solutions.epub`, and one PDF per chapter in `_book/chapters/`.

Official titles and difficulty live in `site/data/titles.json`; refresh with `uv run python site/fetch_titles.py`.
