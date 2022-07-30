package com.leetcode.greedy;

/**
 * @Date: 2019/03/20, 04/25/2020
 * @Description: Greedy
 **/
public class _45_JumpGameII {

    // time: O(n) space: O(1)
    // 更加容易理解。在curMax的范围内找可以走到最大距离nextmax。并且当前点和curMax相同时，表示这里肯定会有跳跃，但记住这不代表终点在这。
        public int jump(int[] nums) {
            if (nums == null || nums.length < 2) {
                return 0;
            }
            int curMax = 0;
            int nextMax = 0;
            int step = 0;
            for (int i = 0; i < nums.length && i <= curMax; i++) {
                nextMax = Math.max(nextMax, i + nums[i]);
                if (i == curMax) {
                    step++;
                    curMax = nextMax;
                    if (curMax >= nums.length - 1) {
                        return step;
                    }
                }
            }
            return -1;
        }

    // https://www.youtube.com/watch?v=r3pZd9ghqxk
    public static int jump2(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        int curMax = 0; // 当前能走的最远位置。
        int nextMax = 0; // 下一步能走的最远能够达到的位置。
        int step = 0;
        int index = 0;
        while (index <= curMax) {
            while (index <= curMax) {
                nextMax = Math.max(nextMax, index + nums[index]);
                index++;
            }
            curMax = nextMax;
            step++;
            if (curMax >= nums.length - 1) {
                return step;
            }
        }
        return 0;
    }

    
}
