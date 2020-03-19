/*
 * @Date: 2019-11-19 10:41:22
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-18 21:05:10
 */
package com.leetcode.array.counter;

public class _53_MaximumSubarray {

    /**
     * 53. Maximum Subarray
     * when: 2019/03/26
     * Review1: 2019/7/28
     * review2: 2019/8/23

     * Difficulty: Easy

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
    // 状态转换方程
    // dp[i] 表示包含i这个元素下的局部最大值。
    public int maxSubArray2(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++){
            // DP 代表前i个最大的连续和的情况，如果有跳过一个，那么就会只有一个元素
            // 不考虑当前nums[i]是否小于0 只考虑dp[i-1] 因为要连续，所以当前这个元素肯定要加入
            dp[i] = nums[i] + (dp[i - 1] < 0 ? 0: dp[i - 1]); //这里表示如果前面有小于0的部分肯定越来越小
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    // 优化空间复杂度
    public int maxSubArray3(int[] nums) {
        int res = nums[0];
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum = Math.max(sum + nums[i], nums[i]); // 上面一模一样的操作
            res = Math.max(res, sum);
        }
        return res;
    }

    // 这里就不用dp数组。
    public int maxSubArray4(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            max = Math.max(sum, max);
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }

    // 分治的解法
    public int maxSubArray5(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }

    public int helper(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int mid = left + (right - left) / 2;
        int leftRes = helper(nums, left, mid);
        int rightRes = helper(nums, mid + 1, right);
        // cross 类似于merge的过程
        int leftMax = nums[mid];
        int rightMax = nums[mid + 1];
        for (int i = mid, temp = 0; i >= left; i--) {
            temp += nums[i];
            if (temp > leftMax) leftMax = temp;
        }

        for (int i = mid + 1, temp = 0; i <= right; i++) {
            temp += nums[i];
            if (temp > rightMax) rightMax = temp;
        }
        return Math.max(leftRes, Math.max(rightRes, leftMax + rightMax));
    }
}
