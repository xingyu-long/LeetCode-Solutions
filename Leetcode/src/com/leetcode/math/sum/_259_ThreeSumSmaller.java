package com.leetcode.math.sum;

import java.util.Arrays;

public class _259_ThreeSumSmaller {
    /**
     * 259. Three Sum Smaller
     * When:2019/8/6
     * Difficulty: Medium
     * @param nums
     * @param target
     * @return
     */
    public static int threeSumSmaller(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        Arrays.sort(nums);
        int res = 0;
        for (int k = 0; k < nums.length - 2; k++) {
            int i = k + 1;
            int j = nums.length - 1;
            while (i < j) {
                if (nums[i] + nums[j] + nums[k] < target) {
                    res += j - i; // 因为k + i + j < target 所以 k + i + < j的数字 < target!!!
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(threeSumSmaller(new int[]{1, 2,3,4,5,6,7,8,9}, 20));
    }
}
