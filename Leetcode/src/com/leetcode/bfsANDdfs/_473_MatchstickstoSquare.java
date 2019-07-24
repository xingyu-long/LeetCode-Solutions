package com.leetcode.bfsANDdfs;

import java.util.Arrays;
import java.util.Collections;

public class _473_MatchstickstoSquare {

    /**
     *  473. Matchsticks to Square
     *  When:2019/7/24
     *  Difficulty: Medium
     * @param nums
     * @return
     */
    public static boolean makesquare(int[] nums) {
        if (nums.length < 4) return false;//表示无法构成正方形
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 4 != 0) return false;
        Arrays.sort(nums); // 应该需要逆序吧！
        int[] buckets = new int[4];
        return dfs(nums.length - 1, nums, buckets, sum / 4);
    }

    public static boolean dfs(int index, int[] nums, int[] buckets, int target) {
        // 这里的i的运行过程，在进栈过程中也有保留，只是通过continue这个跳过了前面的情况。
        //验证是否符合
        if (index < 0) { // 这样就不会 TLE????
            return buckets[0] == target && buckets[1] == target &&
                    buckets[2] == target && buckets[3] == target;
        }
        // 四个桶里塞东西
        for (int i = 0; i < 4; i++) {
            if (buckets[i] + nums[index] > target) {
                continue;
            }
            buckets[i] += nums[index];//每个桶？？？？
            if (dfs(index - 1, nums, buckets, target)) {
                return true;
            }
            buckets[i] -= nums[index];
        }
        return false;
    }


    public static void main(String[] args) {
        makesquare(new int[]{1,1,2,2,2});
    }
}
