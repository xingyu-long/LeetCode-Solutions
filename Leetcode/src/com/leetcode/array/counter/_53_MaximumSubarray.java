package com.leetcode.array.counter;

public class _53_MaximumSubarray {

    /**
     * 53. Maximum Subarray
        when: 2019/03/26

        涉及到的数据结构或者算法：
            DP（动态规划）
        Test case:

         Input: [-2,1,-3,4,-1,2,1,-5,4]
         dp[0] = -2;
         res = -2;

         i = 1:
         dp[1] = 1;
         res = 1;

         i = 2:
         dp[2] = -3 + 1 = -2;
         res = 1;

         i = 3:
         dp[3] = 4;
         res = 4;

         i = 4:
         dp[4] = -1 + 4 = 3;
         res = 4;

         i = 5:
         dp[5] = 3 + 2 = 5;
         res = 5;

         i = 6:
         dp[6] = 5 + 1 = 6;
         res = 6;

         i = 7:
         dp[7] = 1;
         res = 6;

         i = 8:
         dp[8] = 5;
         res = 6;

     * @param nums
     * @return
     */
    //暴力解法。time:O(~ n^2) space:O(1)
    public int maxSubArray(int[] nums) {
        if (nums.length == 1) return nums[0];
        //里面只包含负数 那肯定最大的就是第一个数
        int res = nums[0];
        int sum = 0;
        for (int i = 0; i < nums.length; i++){
            for (int j = i; j < nums.length; j++){
                sum += nums[j];
                res = Math.max(res, sum);
            }
            sum = 0;
        }
        return res;
    }
    //使用动态规划。time:O(n) space:O(n)
    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++){
            dp[i] = nums[i] + (dp[i - 1] < 0 ? 0: dp[i - 1]); //这里表示如果前面有小于0的部分肯定越来越小
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
