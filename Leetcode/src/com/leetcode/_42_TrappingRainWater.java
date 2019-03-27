package com.leetcode;

public class _42_TrappingRainWater {

    /**
     * 42. Trapping Rain Water
     * when: 2019/03/25
     *
     * solution: two-pointer
     * 分别设置左右max，然后与当前的进行相差则就是对应的乘水量
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        int res = 0;
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        while (left < right){
            if (height[left] < height[right]){
                leftMax = Math.max(leftMax, height[left]);
                res += leftMax - height[left];
                left++;
            }
            else {
                rightMax = Math.max(rightMax, height[right]);
                res += rightMax - height[right];
                right--;
            }
        }
        return res;
    }

    public static void main(String[] args){
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }
}
