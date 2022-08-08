package com.leetcode.dynamic_programming.back_check_dp;

import java.util.*;

/**
 * @Date: 06/13/2020
 * @Description: 往后看DP
 **/
public class _368_LargestDivisibleSubset {

    // time:O(n^2) space:O(n^2)
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // DP 往回看
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int n = nums.length;
        List<Integer>[] dp = new List[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new ArrayList<>();
        }
        Arrays.sort(nums); // 之前没有sort
        dp[0].add(nums[0]);
        // [1,2,3]
        for (int i = 1; i < n; i++) {
            int max = 0;
            List<Integer> list = new ArrayList<>();
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (dp[j].size() > max) {
                        max = dp[j].size();
                        list = dp[j];
                    }
                }
            }
            dp[i].add(nums[i]);
            dp[i].addAll(list);
        }
        int maxCount = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dp[i].size() > maxCount) {
                maxCount = dp[i].size();
                res = dp[i];
            }
        }
        return res;
    }

    // time:O(n^2) space:O(n) 利用prev来记录之前的情况，这个做法很不错！！！
    public List<Integer> largestDivisibleSubset2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int n = nums.length;
        Arrays.sort(nums);
        int[] count = new int[n];
        int[] prev = new int[n];

        int max = 0, index = -1;
        for (int i = 0; i < n; i++) {
            count[i] = 1;
            prev[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    if (1 + count[j] > count[i]) {
                        count[i] = 1 + count[j];
                        prev[i] = j;
                    }
                }
            }
            if (count[i] > max) {
                max = count[i];
                index = i;
            }
        }
        List<Integer> res = new ArrayList<>();
        while (index != -1) {
            res.add(nums[index]);
            index = prev[index];
        }
        return res;
    }
}
