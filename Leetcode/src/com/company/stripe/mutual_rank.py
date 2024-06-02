"""
part 1
给一个user map，比如 {a: [c,d], c: [a]}，key是不同的user，value是其他user的ranking。然后实现hasMutualRank(a)，如果a的第一rank user恰好也把a作为第一rank，那么return true

part 2
在第一题的基础上改动，rank作为变量, hasMutualRank(a, rank)。第一题就相当于hasMutualRank(a, 0)

part 3
swap某人index和index-1的位置，返回有哪些人曾经是mutual或者现在是mutaul的关系‍‌‍‌‍‍‍‌‍‌‌‍‍‍‍‌‍‍‌

part 4
Anti-ranking. 比如 {a: [b,c,d], b: [d,c,a]}，b是a的第一rank，同时a是b的倒数第一rank，这个才能算作hasAntiMutualRank。Follow-up是如果这个anti-rank也是像part 3是浮动的怎么‍‌‍‌‍‍‍‌‍‌‌‍‍‍‍‌‍‍‌办
"""

from typing import List, Set


def hasMutualRank(user_map: dict, user: str) -> bool:
    ranks = user_map[user]
    first = ranks[0]
    return user_map[first][0] == user


def hasMutualRank(user_map: dict, user: str, rank: int) -> bool:
    ranks = user_map[user]
    peer = ranks[rank]
    return len(user_map[peer]) > rank and user_map[peer][rank] == user


"""
part 3

wishlist = [
    a : [b, c, d],
    b : [a, c],
    c : [d, a],
    d : [c, b]
]

changed_pair(a, 1)

wishlist = [
    a : [c, b, d],
    b : [a, c],
    c : [d, a],
    d : [c, b]
]

affected: b, c


changed_pair(a, 2)
wishlist = [
    a : [b, d, c],
    b : [a, c],
    c : [d, a],
    d : [c, b]
]

affected: c
"""


def changed_pair(user_map: dict, user: str, pos: int) -> Set[str]:
    curr = user_map[user][pos]
    prev = user_map[user][pos - 1]
    res = set()

    def check_if_mutual_rank(user, pos):
        peer = user_map[user][pos]
        if pos < len(user_map[peer]) and user_map[peer][pos] == user:
            return peer
        return None

    def check_if_mutual_rank_after(user, peer, after_pos):
        if after_pos < len(user_map[peer]) and user_map[peer][after_pos] == user:
            return peer
        return None

    # check the existing mutual rank
    for item, p in [[user, pos], [user, pos - 1]]:
        mutual = check_if_mutual_rank(item, p)
        if mutual:
            res.add(mutual)
    for item, c, p in [[user, curr, pos - 1], [user, prev, pos]]:
        mutual_after = check_if_mutual_rank_after(item, c, p)
        if mutual_after:
            res.add(mutual_after)

    return res


user_map = {"a": ["b", "c", "d"], "b": ["a", "c"], "c": ["d", "a"], "d": ["c", "b"]}
assert changed_pair(user_map, "a", 1) == {"c", "b"}

user_map = {
    "a": ["c", "d"],
    "b": ["d", "a", "c"],
    "c": ["a", "b"],
    "d": ["c", "a", "b"],
}
assert changed_pair(user_map, "d", 1) == {"a"}
assert changed_pair(user_map, "b", 2) == {"c"}
assert len(changed_pair(user_map, "b", 1)) == 0
