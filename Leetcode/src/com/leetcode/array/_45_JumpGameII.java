package com.leetcode.array;

public class _45_JumpGameII {

    /**
     * 45. Jump Game II
     * when: 2019/03/20
     *
     * 这个还是不太会，需要好好记忆以及例子理解
     * [2, 3, 1, 1, 4]
     * @param nums
     * @return
     */
    // time: O(n) space: O(1)
    public static int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int res = 0;
        int curMaxArea = 0; //当前能走的最大距离
        int maxNext = 0; //记录最长走多远（从i=0这个位置开始）
        // 这里的nums.length - 1 很重要，这里的cur表示当前能走的最长距离，
        // 所以当等于i的时候，使用全局最长距离来替换
        for (int i = 0; i < nums.length - 1; i++){
            maxNext = Math.max(maxNext, i + nums[i]);
            if (i == curMaxArea){
                res++;
                curMaxArea = maxNext;
            }
        }
        return res;
    }

    // https://www.youtube.com/watch?v=r3pZd9ghqxk
    public static int jump2(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
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
            if (curMax >= nums.length - 1) return step;
        }
        return 0;
    }

    // 更加容易理解。在curMax的范围内找可以走到最大距离nextmax。并且当前点和curMax相同时，表示这里肯定会有跳跃，但记住这不代表终点在这。
    public int jump3(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int curMax = 0;
        int nextMax = 0;
        int step = 0;
        for (int i = 0; i < nums.length && i <= curMax; i++) {
            nextMax = Math.max(nextMax, i + nums[i]);
            if (i == curMax) {
                step++;
                curMax = nextMax;
                if (curMax >= nums.length - 1) return step;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        int[] nums = {2,4,1,1,1,1,2};
        System.out.println(jump(nums));
    }
}
