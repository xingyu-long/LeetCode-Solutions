/*
 * @Date: 11/06/2019 23:24:55
 * @LastEditTime: 12/18/2020 10:49:29
 * @Description: Forward, Backward.
 */
package com.leetcode.array;

import java.util.Arrays;

public class _334_IncreasingTripletSubsequence {
    //  利用dp time:O(n^2) space:O(n)
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        // 利用和300 题一样的思路 dp[i]表示在i之前有多少个比它小（加上它自己）
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            res = Math.max(res, dp[i]);
        }
        return res >= 3;
    }

    //  相当于分别找出最小的那个数，中间那个数，第三小的数然后返回true
    // time:O(n) space:O(1)
    public boolean increasingTriplet2(int[] nums) {
        int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num <= first) first = num;
            else if (num <= second) second = num;
            else return true;
        }
        return false;
    }

    //https://www.cnblogs.com/grandyang/p/5194599.html
    // 利用forward（记录0,i之间最小的数）和backward（i，n之间最大的数）来记录，
    public boolean increasingTriplet3(int[] nums) {
        if (nums == null || nums.length == 0) return false;
        int n = nums.length;
        int[] forward = new int[n];
        int[] backward = new int[n];
        forward[0] = nums[0];
        backward[n - 1] = nums[n - 1];
        for (int i = 1; i < n; i++) {
            forward[i] = Math.min(nums[i], forward[i - 1]);
        }

        for (int i = n - 2; i >= 0; i--) {
            backward[i] = Math.max(nums[i], backward[i + 1]);
        }

        for (int i = 0; i < n; i++) {
            if (nums[i] > forward[i] && nums[i] < backward[i]) return true;
        }
        return false;
    }

    public boolean increasingTriplet4(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        // 记录到i之前的最小值或i之后的大的值。
        int n = nums.length;
        int[] forward = new int[n];
        Arrays.fill(forward, Integer.MAX_VALUE);
        int[] backward = new int[n];
        Arrays.fill(backward, Integer.MIN_VALUE);
        for (int i = 1; i < n; i++) {
            forward[i] = Math.min(forward[i - 1], nums[i - 1]);
        }
        
        for (int i = n - 2; i >= 0; i--) {
            backward[i] = Math.max(backward[i + 1], nums[i + 1]);
        }
        
        for (int i = 1; i < n - 1; i++) {
            // System.out.println("forward = " + forward[i] + " backward = " + backward[i]);
            if (forward[i] < nums[i] && nums[i] < backward[i]) {
                return true;
            }
        }
        return false;
    }
}