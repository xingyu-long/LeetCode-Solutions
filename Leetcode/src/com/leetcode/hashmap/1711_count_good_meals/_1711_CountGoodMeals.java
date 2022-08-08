/*
 * @Date: 01/07/2021 15:21:07
 * @LastEditTime: 08/08/2022 16:30:53
 * @Description: Two sum
 */
package com.leetcode.hashmap;

import java.util.HashMap;
import java.util.Map;

public class _1711_CountGoodMeals {
    public int countPairs(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        // 类似于two sum
        int mod = (int) Math.pow(10, 9) + 7;
        Map<Integer, Integer> map = new HashMap<>();
        long res = 0;
        for (int num : nums) {
            int power = 1;
            for (int i = 0; i <= 21; i++) {// 最多2^21
                if (map.containsKey(power - num)) {
                    res += map.get(power - num);
                    res %= mod;
                }
                power *= 2;
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return (int) res;
    }
}
