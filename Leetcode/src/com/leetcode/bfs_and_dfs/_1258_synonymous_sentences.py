from collections import defaultdict, deque
from typing import List


class Solution:
    def generateSentences(self, synonyms: List[List[str]], text: str) -> List[str]:
        graph = defaultdict(set)
        for word1, word2 in synonyms:
            graph[word1].add(word2)
            graph[word2].add(word1)

        # BFS
        queue = deque()
        queue.append(text)
        res = set()
        while queue:
            curr = queue.popleft()
            res.add(curr)
            words = curr.split()
            for i, word in enumerate(words):
                if word in graph:
                    for new_word in graph[word]:
                        new_text = " ".join(words[:i] + [new_word] + words[i + 1 :])
                        if new_text not in res:
                            queue.append(new_text)

        return sorted(list(res))
