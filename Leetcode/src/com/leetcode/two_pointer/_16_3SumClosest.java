package com.leetcode.two_pointer;

import java.util.Arrays;

public class _16_3SumClosest {

    /**
     * 16. 3Sum Closest
     * When: 2019/04/10
     * Review1: 2019/8/6
     * review2:10/30/2019
     * solution: 跟普通的3 sum 类似。
     * 这里减2  也可以表示出 start 和end 不同值不然就也不会运行while (start < end)这部分
     * @param nums
     * @param target
     * @return
     */
    // time: O(n^2)
    // space: O(1)
    // 其实不用处理duplicate的情况，因为那时候不会小于diff所以不会有影响。
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int diff = Integer.MAX_VALUE;
        int res = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            int num = nums[i];
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + num;
                if (Math.abs(sum - target) < diff) {
                    diff = Math.abs(sum - target);
                    res = sum;
                }
                if (sum < target) {
                    left++;
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                    right--;
                }
            }
        }
        return res;
    }
}
