/*
 * @Date: 2020-03-30 09:47:39
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-30 09:52:32
 */
package com.leetcode.array.counter;

import java.util.HashMap;

public class _1394_FindLuckyIntegerinanArray {

    public int findLucky(int[] arr) {
        // HashMap
        // time:O(n) space:O(n)
        if (arr == null || arr.length == 0) return -1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int res = -1;
        for (int key : map.keySet()) {
            if (map.get(key) == key) 
                res = Math.max(res, key);
        }
        return res;
    }
}