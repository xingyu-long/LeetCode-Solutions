package com.leetcode.array;

public class _665_NondecreasingArray {
    // 665. Non-decreasing Array
    // 主要思考以下的三个例子
    // [4,2,3], [-1,4,2,3], [2,3,3,2,4]
    // https://www.cnblogs.com/grandyang/p/7565424.html
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0) return true;
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                if (count > 1) return false;
                if (i == 1 || nums[i] >= nums[i - 2]) nums[i - 1] = nums[i];
                else nums[i] = nums[i - 1];
                count++;
            }
        }
        return count <= 1;
    }
}
