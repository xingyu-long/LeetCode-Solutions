"""
Similar to wildcard matching but without using DP solution
"""


def is_match_v1(s: str, p: str) -> bool:
    # at most have 1 * in pattern
    if not s and not p:
        return True
    if not p:
        return False
    if "*" not in p:
        return s == p

    idx = p.index("*")
    prefix, suffix = p[0:idx], p[idx + 1 :]
    if not prefix and not suffix:
        return True
    if not prefix:
        return s.endswith(suffix)
    if not suffix:
        return s.startswith(prefix)

    return s.startswith(prefix) and s[len(prefix) :].endswith(suffix)


def is_match(s: str, p: str) -> bool:
    s_idx = p_idx = 0
    last_wildcard_idx = s_backtrack_idx = -1
    while s_idx < len(s):
        # simply char matching
        if p_idx < len(p) and p[p_idx] == s[s_idx]:
            p_idx += 1
            s_idx += 1
        elif p_idx < len(p) and p[p_idx] == "*":
            last_wildcard_idx = p_idx + 1
            s_backtrack_idx = s_idx
            p_idx += 1
        elif last_wildcard_idx == -1:
            return False
        else:
            p_idx = last_wildcard_idx
            s_idx = s_backtrack_idx + 1
            s_backtrack_idx += 1

    while p_idx < len(p) and p[p_idx] == "*":
        p_idx += 1
    return s_idx == len(s) and p_idx == len(p)


assert is_match("", "*") == True
assert is_match("abcde", "*") == True
assert is_match("cat", "*t") == True
assert is_match("cat", "*t*a*c*") == False
assert is_match("ccc", "*t*a*c**") == False
assert is_match("catanddog", "cat*d*") == True
