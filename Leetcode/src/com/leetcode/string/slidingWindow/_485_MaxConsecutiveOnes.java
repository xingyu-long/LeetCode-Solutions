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
}
