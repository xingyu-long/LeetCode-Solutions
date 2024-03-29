package com.leetcode.stack_priority_queue.monoStack;

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
    //下面的这个栈方法相当于是吧内部的for替代掉了
    //往后看也是为了优化，因为后面那个更大的话 1 * height 肯定是更大的部分，所以也是为什么用小于前面的数开始、

    // 需要注意stack为空以及走到最后的时候。
    // time:O(n) space: O(n)
    public static int largestRectangleArea2(int[] heights) {
        // 利用局部峰值来解决
        if (heights == null || heights.length == 0) return 0;
        Stack<Integer> stack = new Stack<>();
        int res = 0;
        // 找到左边的比它短的i, 右边比他短的j，然后局部max就是(j - i - 1) * h
        for (int i = 0; i <= heights.length; i++) {
            // i可以取n是最后用0作为最低高度然后计算栈内剩下的情况
            int h = i == heights.length ? 0 : heights[i];
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
