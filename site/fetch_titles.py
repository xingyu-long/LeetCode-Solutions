"""Fetch official LeetCode titles, slugs and difficulty into site/data/titles.json.

Usage:
    python site/fetch_titles.py                 # refresh everything
    python site/fetch_titles.py --missing-only  # only add numbers not yet present

Never exits non-zero when LeetCode is unreachable with --missing-only.
"""

import argparse
import json
import sys
import urllib.request
from pathlib import Path

OUT = Path(__file__).parent / "data" / "titles.json"
ALL_URL = "https://leetcode.com/api/problems/all/"
GRAPHQL_URL = "https://leetcode.com/graphql"
DIFFICULTY = {1: "Easy", 2: "Medium", 3: "Hard"}
UA = {"User-Agent": "Mozilla/5.0 (compatible; leetcode-solutions-site)"}

QUERY = """
query problemsetQuestionList($categorySlug: String, $limit: Int, $skip: Int, $filters: QuestionListFilterInput) {
  problemsetQuestionList: questionList(categorySlug: $categorySlug, limit: $limit, skip: $skip, filters: $filters) {
    total: totalNum
    questions: data { frontendQuestionId: questionFrontendId title titleSlug difficulty }
  }
}"""


def from_api():
    req = urllib.request.Request(ALL_URL, headers=UA)
    with urllib.request.urlopen(req, timeout=30) as r:
        data = json.load(r)
    out = {}
    for p in data["stat_status_pairs"]:
        s = p["stat"]
        out[str(s["frontend_question_id"])] = {
            "title": s["question__title"],
            "slug": s["question__title_slug"],
            "difficulty": DIFFICULTY.get(p["difficulty"]["level"], ""),
        }
    return out


def from_graphql():
    out, skip, limit = {}, 0, 100
    while True:
        body = json.dumps(
            {"query": QUERY, "variables": {"categorySlug": "", "skip": skip, "limit": limit, "filters": {}}}
        ).encode()
        req = urllib.request.Request(GRAPHQL_URL, data=body, headers={**UA, "Content-Type": "application/json"})
        with urllib.request.urlopen(req, timeout=30) as r:
            d = json.load(r)["data"]["problemsetQuestionList"]
        for q in d["questions"]:
            out[q["frontendQuestionId"]] = {
                "title": q["title"],
                "slug": q["titleSlug"],
                "difficulty": q["difficulty"].capitalize(),
            }
        skip += limit
        if skip >= d["total"]:
            return out


def main():
    ap = argparse.ArgumentParser()
    ap.add_argument("--missing-only", action="store_true")
    args = ap.parse_args()
    existing = json.loads(OUT.read_text(encoding="utf-8")) if OUT.exists() else {}
    fetched = None
    for fn in (from_api, from_graphql):
        try:
            fetched = fn()
            if fetched:
                break
        except Exception as e:  # noqa: BLE001
            print(f"{fn.__name__} failed: {e}", file=sys.stderr)
    if not fetched:
        print("Could not reach LeetCode; titles.json unchanged.", file=sys.stderr)
        return 0 if args.missing_only else 1
    if args.missing_only:
        added = {k: v for k, v in fetched.items() if k not in existing}
        existing.update(added)
        print(f"Added {len(added)} new titles.")
    else:
        existing = fetched
        print(f"Fetched {len(fetched)} titles.")
    OUT.parent.mkdir(parents=True, exist_ok=True)
    ordered = dict(sorted(existing.items(), key=lambda kv: int(kv[0]) if kv[0].isdigit() else 10**9))
    lines = [f"{json.dumps(k)}: {json.dumps(v, ensure_ascii=False)}" for k, v in ordered.items()]
    OUT.write_text("{\n" + ",\n".join(lines) + "\n}\n", encoding="utf-8")
    return 0


if __name__ == "__main__":
    sys.exit(main())
