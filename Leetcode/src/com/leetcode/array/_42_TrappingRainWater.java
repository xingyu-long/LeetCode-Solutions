package com.leetcode.array;

/**
 * @Date: 2019/03/25, 11/3/2019, 05/13/2020
 * @Description: Two Pointer
 **/
public class _42_TrappingRainWater {

    // time: O(n) space: O(1)
    public static int trap(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        // 先计算最大，然后减去当前（最大后面就会保持住！）
        while (left < right) {
            if (height[left] < height[right]) {
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left];
                left++;
            } else {
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }
}
