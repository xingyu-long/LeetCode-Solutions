/*
 * @Date: 11/17/2020 16:37:02
 * @LastEditTime: 11/26/2020 10:28:19
 * @Description: Sort
 */
package com.leetcode.array.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _1630_Arithmetic_Subarrays {
    // Time: O(N * M * Log M)
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> res = new ArrayList<>();
        int n = l.length;
        for (int i = 0; i < n; i++) {
            int left = l[i], right = r[i];
            List<Integer> list = new ArrayList<>();
            for (int j = left; j <= right; j++) {
                list.add(nums[j]);
            }
            Collections.sort(list);
            Integer prev = null;
            int k = 1;
            for (; k < list.size(); k++) {
                if (prev == null) {
                    prev = list.get(k) - list.get(k - 1);
                } else if (prev != null && list.get(k) - list.get(k - 1) != prev) {
                    res.add(false);
                    break;
                }
            }
            if (k == list.size())
                res.add(true);
        }
        return res;
    }
}
