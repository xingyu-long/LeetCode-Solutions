package com.leetcode.matrix;

import java.util.Stack;

public class  _85_MaximalRectangle {
    // 85. Maximal Rectangle
    // time:O(m * (n + n))
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) return 0;
        int m = matrix.length;
        int n = matrix[0].length;
        int[] heights = new int[matrix[0].length];
        int max = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') heights[j]++;
                else heights[j] = 0;
            }
            max = Math.max(max, helper(heights));
        }
        return max;
    }


    public int helper(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int max = 0;
        for (int i = 0; i <= heights.length; i++) {
            int h = (i == heights.length ? 0 : heights[i]);
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int start = stack.isEmpty() ? -1 : stack.peek();
                max = Math.max(max, (i - start - 1) * height);
            }
            stack.push(i);
        }
        return max;
    }
}
