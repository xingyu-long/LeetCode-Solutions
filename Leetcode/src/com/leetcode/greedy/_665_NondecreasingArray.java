/*
 * @Date: 01/09/2020 10:28:41
 * @LastEditTime: 05/04/2021 09:27:28
 * @Description: Greedy 
 */
package com.leetcode.greedy;

public class _665_NondecreasingArray {
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
