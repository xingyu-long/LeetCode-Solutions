package com.leetcode;

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
    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        int res = 0;
        int curMaxArea = 0;
        int maxNext = 0; //记录最长走多远（从i=0这个位置开始）
        for (int i = 0; i < nums.length - 1; i++){
            maxNext = Math.max(maxNext, i + nums[i]);
            if (i == curMaxArea){
                res++;
                curMaxArea = maxNext;
            }
        }
        return res;
    }
}
