class Solution:
    # compare firsts and lasts
    def areSentencesSimilar(self, sentence1: str, sentence2: str) -> bool:
        q1, q2 = map(deque, (sentence1.split(), sentence2.split()))
        while q1 and q2 and q1[0] == q2[0]:
            q1.popleft()
            q2.popleft()

        while q1 and q2 and q1[-1] == q2[-1]:
            q1.pop()
            q2.pop()

        return len(q1) == 0 or len(q2) == 0
