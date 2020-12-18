/*
 * @Date: 01/19/2020 15:19:36
 * @LastEditTime: 12/17/2020 09:39:40
 * @Description: HashMap
 */
package com.leetcode.array;

import java.util.HashMap;

public class _454_4SumII {
    // time:O(m * n) space:O(m * n)
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        // 利用A,B先求和然后再看CD的和的负数是否能找到。
        HashMap<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        for (int c : C) {
            for (int d : D) {
                int sum = -(c + d);
                res += map.getOrDefault(sum, 0);
            }
        }
        return res;
    }
}
