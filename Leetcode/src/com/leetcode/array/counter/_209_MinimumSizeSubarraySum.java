package com.leetcode.array.counter;

public class _209_MinimumSizeSubarraySum {

    /**
     * 209. Minimum Size Subarray Sum
     * When: 2019/03/26
     *
     * solution:
     *  滑动窗口：表示先加数字，然后判断其与s的大小，如果是大于的情况就while循环让其length减到最小
     *  并且符合sum >= s的情况
     * @param s
     * @param nums
     * @return
     */
    //time: O(n) space:O(1)
    public static int minSubArrayLen(int s, int[] nums) {
        int res = Integer.MAX_VALUE;
        int left = 0, sum = 0;
        for (int i = 0; i < nums.length; i++){
            sum += nums[i];
            while (sum >= s){
                res = Math.min(res, i - left + 1);
                sum -= nums[left++];
            }
        }
        return res == Integer.MAX_VALUE ? 0: res;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,4,4};
        int s = 4;
        System.out.println(minSubArrayLen(s, nums));
    }

}
