package com.leetcode.stackPriorityQueue;

import java.util.Stack;

public class _84_LargestRectangleinHistogram {
    /**
     * 84. Largest Rectangle in Histogram
     * When:2019/9/4
     * Difficulty: Hard
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        // 利用局部峰值来解决
        if (heights == null || heights.length == 0) return 0;
        int res = 0;
        for (int i = 0; i < heights.length; i++) {
            while (i + 1 < heights.length && heights[i] <= heights[i + 1]) {
                i++;
            }
            int minH = heights[i];
            // 然后倒着回去计算
            for (int j = i; j >= 0; j--) {
                minH = Math.min(minH, heights[j]);
                res  = Math.max(res, minH * (i - j + 1));
            }
        }
        return res;
    }

    // 这里的start没有搞懂
    public static int largestRectangleArea2(int[] heights) {
        // 利用局部峰值来解决
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = i == heights.length ? 0 : heights[i]; // 为了可以计算最后一次
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int start = stack.isEmpty() ? -1 : stack.peek();
                int area = height * (i - start - 1);
                res = Math.max(res, area);
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] heights = new int[]{2,1,5,6,2,3};
        largestRectangleArea2(heights);
    }
}
