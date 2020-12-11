/*
 * @Date: 12/08/2020 09:33:40
 * @LastEditTime: 12/08/2020 09:34:56
 * @Description: TWo sum, hashmap
 */
package com.leetcode.HashMap;

import java.util.HashMap;
import java.util.Map;

public class _1010_PairsOfSongsWithTotalDurationsDivisibleBy60 {
    // time: O(n) space:O(n)
    public int numPairsDivisibleBy60(int[] time) {
        if (time == null || time.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int t : time) {
            t %= 60;
            if (map.containsKey(60 - t)) {
                res += map.get(60 - t);
            } else if (t == 0 && map.containsKey(t)) {
                // 注意考虑这种情况
                res += map.get(t);
            }
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        return res;
    }
}
