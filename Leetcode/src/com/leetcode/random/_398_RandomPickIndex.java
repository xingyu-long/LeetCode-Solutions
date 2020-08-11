package com.leetcode.random;

import java.util.Random;

public class _398_RandomPickIndex {
    private int[] nums;
    private Random rmd;

    /**
     *  398. Random Pick Index
        When: 2019/06/20

        solution: 蓄水池抽样法，让其概率均等
     * @param nums
     */
    public _398_RandomPickIndex(int[] nums) {
        this.nums = nums;
        rmd = new Random();
    }

    public int pick(int target) {
        // 这里就是先要找到等于target的情况，然后随机一个出来（每个机会均等）
        int res = -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != target) continue; // 相当于一定要找到那个target相同的情况
            if (rmd.nextInt(++count) == 0) {
                res = i;
            }
        }
        return res;
    }

}
