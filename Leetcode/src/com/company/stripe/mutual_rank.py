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


def hasMutualRank(user_map: dict, user: str) -> bool:
    ranks = user_map[user]
    first = ranks[0]
    return user_map[first][0] == user


def hasMutualRank(user_map: dict, user: str, rank: int) -> bool:
    ranks = user_map[user]
    peer = ranks[rank]
    return len(user_map[peer]) > rank and user_map[peer][rank] == user
