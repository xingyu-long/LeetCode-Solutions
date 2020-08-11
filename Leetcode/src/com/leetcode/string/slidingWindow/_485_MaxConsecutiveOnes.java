package com.leetcode.string.slidingWindow;

public class _485_MaxConsecutiveOnes {

    /**
     *  485. Max Consecutive Ones
     *  When:2019/7/22
     *  review1:2019/8/29
     *  Difficulty: Easy
     *
     *  不要想太多了，按照常规思路去做就好
     * @param nums
     * @return
     */

    // time:O(n) space:O(1)
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res = 0;
        int count = 0;
        for (int num : nums) {
            if (num == 1) {
                count ++;
                res = Math.max(res, count);
            } else {
                count = 0; // 一旦不连续就从头开始
            }
        }
        return res;
    }

    // sliding window
    public int findMaxConsecutiveOnes2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int count = 0;
        int start = 0, end = 0;
        int res = 0;
        while (end < nums.length) {
            if (nums[end] != 1) count++;
            while (count > 0) { // 只能有一种。
                if (nums[start] != 1) count--;
                start++;
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }
}
