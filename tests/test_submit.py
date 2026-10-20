import subprocess
import sys
from datetime import datetime
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent.parent))

import submit  # noqa: E402

NOW = datetime(2026, 10, 19, 12, 30, 5)


def test_parse_porcelain_handles_rename_untracked_and_spaces():
    out = (
        "R  new/_1_Two.java\0old/1_two.java\0"
        "?? Leetcode/src/com/leetcode/array/_2_Add Two.java\0"
        " D gone.py\0"
    )
    assert submit.parse_porcelain(out) == [
        ("R ", "new/_1_Two.java"),
        ("??", "Leetcode/src/com/leetcode/array/_2_Add Two.java"),
        (" D", "gone.py"),
    ]


def test_problems_dedupe_languages_and_prefer_official_titles():
    paths = [
        "Leetcode/src/com/leetcode/array/_56_MergeIntervals.java",
        "Leetcode/src/com/leetcode/array/_56_merge_intervals.py",
        "Leetcode/src/com/leetcode/array/_57_insert_interval.py",
        "Leetcode/src/com/leetcode/array/Helper.java",
    ]
    problems = submit.problems_from_paths(paths, {"56": "Merge Intervals!"})
    assert problems == {56: "Merge Intervals!", 57: "Insert Interval"}


def test_build_message_lists_problems_and_truncates():
    problems = {n: f"P{n}" for n in range(1, 12)}
    message = submit.build_message(NOW, problems, [])
    assert message.startswith("2026-10-19-12:30:05 Changes were made in")
    assert "1. P1, 2. P2" in message
    assert message.endswith("and 3 more")


def test_build_message_falls_back_to_file_names():
    message = submit.build_message(NOW, {}, ["docs/notes.md"])
    assert message.endswith("notes.md")


def run(cwd, *args):
    subprocess.run(args, cwd=cwd, check=True, capture_output=True)


def test_default_run_commits_only_solutions(tmp_path, monkeypatch):
    run(tmp_path, "git", "init", "-q", "-b", "main")
    run(tmp_path, "git", "config", "user.email", "t@example.com")
    run(tmp_path, "git", "config", "user.name", "Test")
    (tmp_path / "README.md").write_text("hi\n")
    run(tmp_path, "git", "add", "-A")
    run(tmp_path, "git", "commit", "-q", "-m", "init")

    solution = tmp_path / submit.SOLUTIONS_DIR / "array" / "_1_two_sum.py"
    solution.parent.mkdir(parents=True)
    solution.write_text("pass\n")
    (tmp_path / "README.md").write_text("changed\n")
    run(tmp_path, "git", "add", "README.md")

    monkeypatch.setattr(submit, "ROOT", tmp_path)
    monkeypatch.setattr(submit, "TITLES_FILE", tmp_path / "missing.json")
    assert submit.main(["--no-push"]) == 0

    committed = subprocess.run(
        ["git", "show", "--name-only", "--format=%s", "HEAD"],
        cwd=tmp_path, capture_output=True, text=True, check=True,
    ).stdout
    assert "1. Two Sum" in committed
    assert "_1_two_sum.py" in committed
    assert "README.md" not in committed
    status = subprocess.run(
        ["git", "status", "--short"],
        cwd=tmp_path, capture_output=True, text=True, check=True,
    ).stdout
    assert "README.md" in status


def test_dry_run_changes_nothing(tmp_path, monkeypatch, capsys):
    run(tmp_path, "git", "init", "-q", "-b", "main")
    run(tmp_path, "git", "config", "user.email", "t@example.com")
    run(tmp_path, "git", "config", "user.name", "Test")
    (tmp_path / "a.txt").write_text("a\n")
    run(tmp_path, "git", "add", "-A")
    run(tmp_path, "git", "commit", "-q", "-m", "init")
    solution = tmp_path / submit.SOLUTIONS_DIR / "_9_Palindrome.java"
    solution.parent.mkdir(parents=True)
    solution.write_text("class X {}\n")

    monkeypatch.setattr(submit, "ROOT", tmp_path)
    monkeypatch.setattr(submit, "TITLES_FILE", tmp_path / "missing.json")
    assert submit.main(["--dry-run"]) == 0
    assert "Dry run" in capsys.readouterr().out
    count = subprocess.run(
        ["git", "rev-list", "--count", "HEAD"],
        cwd=tmp_path, capture_output=True, text=True, check=True,
    ).stdout.strip()
    assert count == "1"
