import sys
from pathlib import Path

sys.path.insert(0, str(Path(__file__).resolve().parent.parent / "site"))

import build  # noqa: E402
import pytest  # noqa: E402


def make_tree(root: Path, files: list[str]) -> Path:
    src = root / "src"
    for f in files:
        p = src / f
        p.parent.mkdir(parents=True, exist_ok=True)
        p.write_text("// code\n", encoding="utf-8")
    return src


def run(tmp_path, files, titles=None):
    src = make_tree(tmp_path, files)
    warnings = []
    problems = build.discover(src, tmp_path, warn=warnings.append)
    build.apply_titles(problems, titles or {}, warn=warnings.append)
    return problems, build.link_categories(problems), warnings


@pytest.mark.parametrize(
    "name,expected",
    [
        ("_56_MergeIntervals.java", (56, "MergeIntervals", "java")),
        ("_56_merge_intervals.py", (56, "merge_intervals", "py")),
        ("_1_TwoSum.java", (1, "TwoSum", "java")),
    ],
)
def test_filename_good(name, expected):
    assert build.parse_filename(name) == expected


@pytest.mark.parametrize(
    "name",
    [
        "46_permutation.py",
        "547_number_of_provinces.py",
        "_804UniqueMorseCodeWords.java",
        "1762._buildings_with_an_ocean_view.py",
        "TreeNode.java",
        "_ThePowerSum.java",
    ],
)
def test_filename_near_misses(name):
    assert build.parse_filename(name) is None


def test_near_miss_warns_but_helpers_do_not(tmp_path):
    problems, _, warnings = run(tmp_path, ["array/46_permutation.py", "array/_ThePowerSum.java", "common/TreeNode.java"])
    assert not problems
    assert len(warnings) == 1 and "46_permutation.py" in warnings[0]


def test_unknown_extension_warns(tmp_path):
    problems, _, warnings = run(tmp_path, ["array/_1_TwoSum.xyz"])
    assert not problems and "unknown extension" in warnings[0]


def test_grouping_by_number_across_languages(tmp_path):
    problems, cats, _ = run(tmp_path, ["array/_56_MergeIntervals.java", "array/_56_merge_intervals.py"])
    assert len(problems) == 1
    p = problems[("array", 56)]
    assert p.lang_names() == ["Java", "Python"]


def test_same_language_twice_stacks(tmp_path):
    problems, _, _ = run(tmp_path, ["backtracking/_46_permutation.py", "backtracking/_46_permutations.py"])
    (lang, files), = problems[("backtracking", 46)].tabs()
    assert lang == "Python" and len(files) == 2


def test_tab_order_java_python_then_alpha(tmp_path):
    problems, _, _ = run(tmp_path, ["a/_1_x.go", "a/_1_x.py", "a/_1_X.java", "a/_1_x.cpp"])
    assert problems[("a", 1)].lang_names() == ["Java", "Python", "C++", "Go"]


def test_root_file_goes_to_others(tmp_path):
    problems, cats, _ = run(tmp_path, ["_30_Foo.java", "array/_1_TwoSum.java"])
    assert ("others", 30) in problems
    assert cats[-1].key == "others" and cats[-1].name == "Others"


def test_others_last_even_if_largest(tmp_path):
    _, cats, _ = run(tmp_path, ["_1_A.java", "_2_B.java", "_3_C.java", "array/_4_D.java"])
    assert [c.key for c in cats] == ["array", "others"]


def test_common_folder_skipped(tmp_path):
    problems, _, _ = run(tmp_path, ["common/_5_Hidden.java"])
    assert not problems


def test_subtopic_detection_and_order(tmp_path):
    problems, cats, _ = run(
        tmp_path,
        ["array/_1_A.java", "array/interval/_2_B.java", "array/sub_array/_3_C.java", "array/a/b/_4_D.java"],
    )
    groups = cats[0].groups()
    assert [g[1] for g in groups] == ["General", "A / B", "Interval", "Sub-array"]
    assert problems[("array", 4)].sub_name == "A / B"


@pytest.mark.parametrize(
    "key,name",
    [
        ("stack_priority_queue", "Stack and queue"),
        ("bfs_and_dfs", "BFS and DFS"),
        ("linkedList", "Linked list"),
        ("ood", "OOD"),
        ("dynamic_programming", "Dynamic programming"),
        ("hashmap", "Hashmap"),
        ("new_cool_thing", "New cool thing"),
        ("someNewTopic", "Some new topic"),
    ],
)
def test_category_names(key, name):
    assert build.category_name(key) == name


@pytest.mark.parametrize(
    "path,name",
    [("sub_array", "Sub-array"), ("twoPointer", "Two pointer"), ("monoStack", "Mono stack"), ("a/monoStack", "A / Mono stack")],
)
def test_subtopic_names(path, name):
    assert build.subtopic_name(path) == name


def test_sidebar_order_by_count(tmp_path):
    _, cats, _ = run(tmp_path, ["a/_1_A.java", "b/_2_B.java", "b/_3_C.java"])
    assert [c.key for c in cats] == ["b", "a"]


def test_problem_in_two_categories(tmp_path):
    problems, cats, _ = run(tmp_path, ["greedy/_55_JumpGame.java", "array/_55_JumpGame.java"])
    a, g = problems[("array", 55)], problems[("greedy", 55)]
    assert a.canonical and not g.canonical
    assert a.also_in == [g] and g.also_in == [a]
    assert a.dir != g.dir


def test_missing_title_fallback(tmp_path):
    problems, _, warnings = run(tmp_path, ["a/_9_merge_intervals.py", "a/_9_MergeIntervals.java"])
    p = problems[("a", 9)]
    assert p.title == "Merge Intervals" and p.slug == "merge-intervals" and p.lc_url == ""
    assert any("no official title for 9" in w for w in warnings)


def test_official_title_used(tmp_path):
    titles = {"56": {"title": "Merge Intervals", "slug": "merge-intervals", "difficulty": "Medium"}}
    problems, _, warnings = run(tmp_path, ["a/_56_x.py"], titles)
    p = problems[("a", 56)]
    assert p.difficulty == "Medium" and p.lc_url == "https://leetcode.com/problems/merge-intervals/"
    assert not warnings


def test_title_from_filename():
    assert build.title_from_filename("MergeIntervals") == "Merge Intervals"
    assert build.title_from_filename("merge_intervals") == "Merge Intervals"
