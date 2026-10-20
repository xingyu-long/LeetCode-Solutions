"""Commit solution changes with a descriptive message and push them.

By default only changes under Leetcode/src/com/leetcode are committed, so
unrelated edits (site code, docs, config) never ride along by accident.

    uv run python submit.py              # commit solutions and push
    uv run python submit.py --dry-run    # show what would happen, change nothing
    uv run python submit.py --no-push    # commit but don't push
    uv run python submit.py --all        # commit every change in the repo
    uv run python submit.py -m "message" # use your own commit message
"""

import argparse
import json
import re
import subprocess
import sys
from datetime import datetime
from pathlib import Path

ROOT = Path(__file__).resolve().parent
SOLUTIONS_DIR = "Leetcode/src/com/leetcode"
TITLES_FILE = ROOT / "site" / "data" / "titles.json"
NUMBERED_RE = re.compile(r"^_(\d+)_(.+)\.[A-Za-z0-9]+$")
MESSAGE_HINT = "Changes were made in following file(s): "
MAX_LISTED = 8


class SubmitError(Exception):
    pass


def git(*args: str, check: bool = True) -> str:
    result = subprocess.run(
        ["git", *args],
        cwd=ROOT,
        capture_output=True,
        text=True,
        encoding="utf-8",
    )
    if check and result.returncode != 0:
        detail = (result.stderr or result.stdout).strip()
        raise SubmitError(f"`git {' '.join(args)}` failed: {detail}")
    return result.stdout


def parse_porcelain(output: str) -> list[tuple[str, str]]:
    """Parse `git status --porcelain=v1 -z` into (status, path) pairs.

    The -z format keeps unusual filenames unquoted. A rename or copy entry is
    followed by an extra NUL-separated field holding the original path.
    """
    fields = output.split("\0")
    changes = []
    i = 0
    while i < len(fields):
        entry = fields[i]
        i += 1
        if len(entry) < 4:
            continue
        status, path = entry[:2], entry[3:]
        if "R" in status or "C" in status:
            i += 1
        changes.append((status, path))
    return changes


def is_solution(path: str) -> bool:
    return path.startswith(SOLUTIONS_DIR + "/")


def title_from_stem(stem: str) -> str:
    words = re.findall(r"[A-Z]+(?![a-z])|[A-Z]?[a-z]+|\d+", stem.replace("_", " "))
    return " ".join(w if w.isupper() else w.capitalize() for w in words)


def load_titles() -> dict[str, str]:
    try:
        data = json.loads(TITLES_FILE.read_text(encoding="utf-8"))
    except (OSError, ValueError):
        return {}
    return {num: info["title"] for num, info in data.items() if "title" in info}


def problems_from_paths(
    paths: list[str], titles: dict[str, str]
) -> dict[int, str]:
    """Map problem number -> title for every numbered solution file."""
    problems: dict[int, str] = {}
    for path in sorted(paths):
        match = NUMBERED_RE.match(Path(path).name)
        if not match:
            continue
        number = int(match.group(1))
        if number not in problems:
            problems[number] = titles.get(str(number)) or title_from_stem(
                match.group(2)
            )
    return problems


def build_message(
    now: datetime, problems: dict[int, str], other_files: list[str]
) -> str:
    stamp = now.strftime("%Y-%m-%d-%H:%M:%S")
    if problems:
        items = [f"{n}. {title}" for n, title in sorted(problems.items())]
    else:
        items = [Path(p).name for p in sorted(other_files)]
    shown, rest = items[:MAX_LISTED], len(items) - MAX_LISTED
    text = ", ".join(shown) + (f" and {rest} more" if rest > 0 else "")
    return f"{stamp} {MESSAGE_HINT}{text}"


def current_branch() -> str:
    branch = git("branch", "--show-current").strip()
    if not branch:
        raise SubmitError("HEAD is detached. Check out a branch first.")
    return branch


def push(branch: str) -> None:
    upstream = git("rev-parse", "--abbrev-ref", "@{u}", check=False).strip()
    if upstream:
        git("fetch")
        behind = git("rev-list", "--count", "HEAD..@{u}").strip()
        if behind != "0":
            raise SubmitError(
                f"{upstream} is {behind} commit(s) ahead of {branch}. "
                "Your commit is saved locally; run `git pull --rebase` and "
                "then submit again."
            )
        git("push")
    else:
        remotes = git("remote").split()
        if not remotes:
            raise SubmitError("No git remote is configured.")
        git("push", "-u", remotes[0], branch)


def main(argv: list[str] | None = None) -> int:
    parser = argparse.ArgumentParser(description=__doc__.split("\n")[0])
    parser.add_argument("--all", action="store_true", help="commit every change")
    parser.add_argument("--no-push", action="store_true", help="skip the push")
    parser.add_argument("--dry-run", action="store_true", help="change nothing")
    parser.add_argument("-m", "--message", help="custom commit message")
    args = parser.parse_args(argv)

    try:
        git("rev-parse", "--is-inside-work-tree")
        branch = current_branch()
        changes = parse_porcelain(
            git("status", "--porcelain=v1", "-z", "--untracked-files=all")
        )
        paths = [path for _, path in changes]
        included = paths if args.all else [p for p in paths if is_solution(p)]
        skipped = [] if args.all else [p for p in paths if not is_solution(p)]

        if not included:
            print("Nothing to commit.")
            if skipped:
                print(f"{len(skipped)} other change(s) were left alone; "
                      "use --all to include them.")
            return 0

        solution_paths = [p for p in included if is_solution(p)]
        message = args.message or build_message(
            datetime.now(),
            problems_from_paths(solution_paths, load_titles()),
            included,
        )

        print(f"Branch:  {branch}")
        print(f"Message: {message}")
        print(f"Commit:  {len(included)} file(s)")
        for status, path in changes:
            if path in included:
                print(f"  {status} {path}")
        if skipped:
            print(f"Left alone ({len(skipped)}), use --all to include:")
            for path in skipped[:10]:
                print(f"  {path}")
            if len(skipped) > 10:
                print(f"  … and {len(skipped) - 10} more")

        if args.dry_run:
            print("\nDry run: nothing was committed or pushed.")
            return 0

        if args.all:
            git("add", "--all")
            git("commit", "-m", message)
        else:
            git("add", "--all", "--", SOLUTIONS_DIR)
            git("commit", "-m", message, "--", SOLUTIONS_DIR)
        print("\nCommitted.")

        if args.no_push:
            print("Skipped the push (--no-push).")
        else:
            push(branch)
            print("Pushed.")
    except SubmitError as error:
        print(f"Error: {error}", file=sys.stderr)
        return 1
    return 0


if __name__ == "__main__":
    sys.exit(main())
