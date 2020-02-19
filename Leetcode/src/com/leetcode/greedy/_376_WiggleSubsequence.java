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


    // time:O(n) space:O(n)
    public int wiggleMaxLength2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] up = new int[n];
        int[] down = new int[n];
        up[0] = 1;
        down[0] = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) { // up
                up[i] = down[i - 1] + 1;
                down[i] = down[i - 1];
            } else if (nums[i] < nums[i - 1]) { // down
                down[i] = up[i - 1] + 1;
                up[i] = up[i - 1];
            } else {
                down[i] = down[i - 1];
                up[i] = up[i - 1];
            }
        }
        return Math.max(down[n - 1], up[n - 1]);
    }

    // time:O(n) space:O(1)
    public int wiggleMaxLength3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int up = 1;
        int down = 1;
        for (int i = 1; i < n; i++) {
            if (nums[i] > nums[i - 1]) { // up
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) { // down
                down = up + 1;
            }
        }
        return Math.max(down, up);
    }
}
