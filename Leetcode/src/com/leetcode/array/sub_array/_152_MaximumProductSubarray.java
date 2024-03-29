package com.leetcode.array.sub_array;

public class _152_MaximumProductSubarray {
    //time: O(n^2) space:O(1)
    public int maxProduct(int[] nums) {
         if (nums.length == 1) return nums[0];
         int res = nums[0];
         int prod = 1;
         for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                prod *= nums[j];
                res = Math.max(res, prod);
            }
            prod = 1;
         }
         return res;
    }
    // solution2: 三种情况，一种是最小，一种最大，一种当前数字 三个比较值则就是所求
    // time:O(n) space:O(n)
    public int maxProduct2(int[] nums) {
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

    // 利用四个变量取代，因为只需要max,prevMax, min, prevMin.
    public int maxProduct3(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int min = nums[0];
        int prevMin = nums[0];
        int max = nums[0];
        int prevMax = nums[0];
        int res = nums[0];
        for (int  i = 1; i < n; i++) {
            max = Math.max(nums[i], Math.max(prevMin * nums[i], prevMax * nums[i]));
            min = Math.min(nums[i], Math.min(prevMin * nums[i], prevMax * nums[i]));
            res = Math.max(max, res);
            prevMax = max;
            prevMin = min;
        }
        return res;
    }
}
