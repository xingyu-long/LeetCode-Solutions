package com.leetcode.array;

public class _11_ContainerWithMostWater {

    /**
     * 11. Container With Most Water
     * when: 2019/03/22
     *
     *
     * solution 1：
     * 两个for循环，分别计算每次的值，然后保留一个最大值即可
     * solution2:
     * 分别从前后开始扫描，计算其面积。
     * 然后左边小于右边则左边+1，反之同理
     *
     *
     * @param height
     * @return
     */
    //time:O(n^2) space:O(1)
    public int maxArea(int[] height) {
        //solution 1: 两个for循环
        int res = 0;
        for (int i = 0; i < height.length; i++)
            for (int j = i + 1; j < height.length; j++){
                res = Math.max(res, Math.min(height[i], height[j]) * (j - i));
            }
        return res;
    }

    //time:O(n) space:O(1)
    public int maxArea2(int[] height) {
        int res = 0;
        int l = 0;
        int r = height.length - 1;
        while (l < r){
            res = Math.max(res, Math.min(height[l], height[r]) * (r - l));
            if(height[l] < height[r]) l++;
            else r--;
        }
        return res;
    }
}
