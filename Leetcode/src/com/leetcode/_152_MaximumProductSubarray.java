package com.leetcode;

public class _152_MaximumProductSubarray {

    /**
     * 152. Maximum Product Subarray
     * When: 2019/03/27
     *
     * solution:
     * 类似于No.53 可以用暴力解法
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {

        /** solution1: brute solution
         if (nums.length == 1) return nums[0];
         int res = nums[0];
         int prod = 1;
         for (int i = 0; i < nums.length; i++){
         for (int j = i; j < nums.length; j++){
         prod *= nums[j];
         res = Math.max(res, prod);
         }
         prod = 1;
         }
         return res;
         **/

        // solution2: 三种情况，一种是最小，一种最大，一种当前数字 三个比较值则就是所求
        int res = nums[0];
        int[] max = new int[nums.length];
        max[0] = nums[0];
        int[] min = new int[nums.length];
        min[0] = nums[0];
        for (int i = 1; i < nums.length; i++){
            max[i] = Math.max(Math.max(max[i - 1] * nums[i], min[i - 1] * nums[i]), nums[i]);
            min[i] = Math.min(Math.min(max[i - 1] * nums[i], min[i - 1] * nums[i]), nums[i]);
            res = Math.max(res, max[i]);
        }
        return res;
    }
}
