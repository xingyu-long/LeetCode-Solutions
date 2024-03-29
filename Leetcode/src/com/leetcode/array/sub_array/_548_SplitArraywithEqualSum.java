package com.leetcode.array.sub_array;

import java.util.HashSet;

/**
 * @Date: 06/23/2020
 * @Description: prefix,
 **/
public class _548_SplitArraywithEqualSum {

    public boolean splitArray(int[] nums) {
        // prefix sum;
        if (nums == null || nums.length == 0) {
            return false;
        }
        int n = nums.length;
        int[] prefix = new int[n];
        /*
        i = 1 j = 3;
        i+1, j - 1
         0 1 2 3
         1 2 3
         0 1 3 6

         x, y -> [y+1] - [x]
        */
        prefix[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }
        // 先确定j的位置
        for (int j = 3; j < n - 3; j++) {
            HashSet<Integer> set = new HashSet<>();
            for (int i = 1; i + 1 < j; i++) {
                int first = prefix[i - 1];
                int second = prefix[j - 1] - prefix[i];
                if (first == second) {
                    set.add(first);
                }
            }

            for (int k = j + 2; k < n - 1; k++) {
                int third = prefix[k - 1] - prefix[j];
                int fourth = prefix[n - 1] - prefix[k];
                if (third == fourth && set.contains(fourth)) {
                    return true;
                }
            }
        }
        return false;
    }
}
