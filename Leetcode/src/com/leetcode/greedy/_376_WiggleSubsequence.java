package com.leetcode.greedy;

public class _376_WiggleSubsequence {
    /**
     *  376.Wiggle Subsequence
     *  When:2019/7/13
     *  Difficulty: Medium
     *
     * @param nums
     * @return
     */
    // time:O(n) space:O(1)
    public int wiggleMaxLength(int[] nums) {
        // 利用状态机思想解决
        if (nums.length < 2) {
            return nums.length;
        }
        final int BEGIN = 0;
        final int UP = 1;
        final int DOWN = 2;

        int STATE = BEGIN;
        int res = 1;

        for (int i = 1; i < nums.length; i++) {
            switch (STATE) {
                case BEGIN:
                    if (nums[i] > nums[i - 1]) {
                        STATE = UP;
                        res++;
                    } else if (nums[i] < nums[i - 1]) {
                        STATE = DOWN;
                        res++;
                    }
                    break;
                case UP:
                    if (nums[i] < nums[i - 1]) {
                        STATE = DOWN;
                        res++;
                    }
                    break;
                case DOWN:
                    if (nums[i] > nums[i - 1]) {
                        STATE = UP;
                        res++;
                    }
                    break;
            }
        }
        return res;
    }
}
