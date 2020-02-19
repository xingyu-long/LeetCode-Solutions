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

    // time:O(nlogn)space:O(n + 1)
    public int minSubArrayLen2(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] sum = new int[nums.length + 1];
        for (int i = 1; i < sum.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i + 1 < sum.length; i++) {
            int end = find(sum, i + 1, sum.length - 1, sum[i] + s);
            if (end != -1) {
                min = Math.min(min, end - i); // 因为是presum的原因，中间的个数不用再加1
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;

    }

    public int find(int[] nums, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) right = mid;
            else left = mid;
        }
        if (nums[left] >= target) return left;
        if (nums[right] >= target) return right;
        return -1;
    }
    public static void main(String[] args){
        int[] nums = new int[]{1,4,4};
        int s = 4;
        System.out.println(minSubArrayLen(s, nums));
    }

}
