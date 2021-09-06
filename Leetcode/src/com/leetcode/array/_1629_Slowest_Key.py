'''
Date: 09/06/2021 15:08:14
LastEditTime: 09/06/2021 15:10:19
Description: logical 
'''


from typing import List


class Solution:
    def slowestKey(self, releaseTimes: List[int], keysPressed: str) -> str:
        res, max_dur = keysPressed[0], releaseTimes[0]
        for i in range(1, len(keysPressed)):
            dur = releaseTimes[i] - releaseTimes[i - 1]
            if dur > max_dur or (dur == max_dur and keysPressed[i] > res):
                res = keysPressed[i]
                max_dur = dur
        return res
