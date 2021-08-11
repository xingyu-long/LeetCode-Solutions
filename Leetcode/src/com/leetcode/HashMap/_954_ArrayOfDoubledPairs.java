/*
 * @Date: 08/11/2021 13:50:18
 * @LastEditTime: 08/11/2021 13:51:25
 * @Description: You need to fill out
 */
package com.leetcode.HashMap;

import java.util.Map;
import java.util.TreeMap;

public class _954_ArrayOfDoubledPairs {
    // Time: O(NlogK)
    // 从小的开始计算，然后找其中的对应关系
    public boolean canReorderDoubled(int[] arr) {
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : map.keySet()) {
            if (map.get(num) == 0)
                continue;
            int want = num < 0 ? num / 2 : num * 2;
            if (num < 0 && num % 2 != 0 || map.get(num) > map.getOrDefault(want, 0)) {
                return false;
            }
            map.put(want, map.get(want) - map.get(num));
        }

        return true;
    }
}
