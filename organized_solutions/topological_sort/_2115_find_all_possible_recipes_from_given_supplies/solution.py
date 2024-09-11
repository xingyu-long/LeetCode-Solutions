'''
Date: 08/26/2022 14:48:37
LastEditTime: 08/26/2022 14:49:39
Description: Topological sort
'''


from collections import defaultdict, deque
from typing import List


class Solution:
    #time: O(N + E)
    def findAllRecipes(self, recipes: List[str], ingredients: List[List[str]], supplies: List[str]) -> List[str]:
        """
        yeast + flour
             |
             bread + meat
                   |
                   sandwich
        """
        receipes_dict = set(recipes)
        d = defaultdict(set)
        indegree = defaultdict(lambda: 0)
        for idx, food in enumerate(recipes):
            ingredient = ingredients[idx]
            for item in ingredient:
                d[item].add(food)
                indegree[food] += 1

        queue = deque()
        for supply in supplies:
            if indegree[supply] == 0:
                queue.append(supply)
        res = []
        while queue:
            size = len(queue)
            for _ in range(size):
                curr = queue.popleft()
                if curr in receipes_dict:
                    res.append(curr)
                if curr in d:
                    for nxt in d[curr]:
                        indegree[nxt] -= 1
                        if indegree[nxt] == 0:
                            queue.append(nxt)
        return res
