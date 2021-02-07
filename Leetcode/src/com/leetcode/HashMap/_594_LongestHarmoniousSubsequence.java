/*
 * @Date: 12/25/2020 10:54:02
 * @LastEditTime: 02/04/2021 09:48:49
 * @Description: HashMap
 */
package com.leetcode.HashMap;

import java.util.HashMap;
import java.util.Map;

public class _594_LongestHarmoniousSubsequence {
    public int findLHS(int[] nums) {
        // 一开始想错成DP，想复杂了
        // 再次想复杂了。
        if (nums == null || nums.length == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = 0;
        for (int num : map.keySet()) {
            if (map.containsKey(num + 1)) {
                res = Math.max(res, map.get(num) + map.get(num + 1));
            }
        }
        return res;
    }
}

