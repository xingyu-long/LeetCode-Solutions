/*
 * @Date: 07/24/2022 10:49:11
 * @LastEditTime: 08/08/2022 16:31:01
 * @Description: HashMap
 */
package com.leetcode.hashmap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _2354_NumberofExcellentPairs {
    // bits(num1 OR num2) + bits(num1 AND num2) = bits(num1) + bits(num2)
    // time: O(n)
    // space: O(n)
    public long countExcellentPairs(int[] nums, int k) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int num : nums) {
            int x = Integer.bitCount(num);
            map.putIfAbsent(x, new HashSet<>());
            map.get(x).add(num);
        }

        long res = 0;
        Set<Integer> visited = new HashSet<>();
        for (int num : nums) {
            if (visited.contains(num))
                continue;
            int need = Math.max(0, k - Integer.bitCount(num));
            for (int key : map.keySet()) {
                if (key >= need) {
                    res += (long) map.get(key).size();
                }
            }
            visited.add(num);
        }
        return res;
    }
}
