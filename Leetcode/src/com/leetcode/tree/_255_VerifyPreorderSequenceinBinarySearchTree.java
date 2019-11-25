package com.leetcode.tree;

import java.util.Stack;

public class _255_VerifyPreorderSequenceinBinarySearchTree {
    /**
     * 255. Verify Preorder Sequence in Binary Search Tree
     * When:2019/9/24
     * @param preorder
     * @return
     */
    // 利用bst性质来做
    public static boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stack = new Stack<>();
        int min = Integer.MIN_VALUE;
        for (int num : preorder) {
            if (num < min) {
                return false;
            }
            while (!stack.isEmpty() && num > stack.peek()) {
                min = stack.pop();
            }
            stack.push(num);
        }
        return true;
    }

    // 还是模仿reconstruct树那个思路
    public static boolean verifyPreorder2(int[] preorder) {
        return helper(preorder, 0, preorder.length - 1, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static boolean helper(int[] preorder, int start, int end, int lower, int upper) {
        if (start > end) return true;
        int val = preorder[start], i = 0;
        if (val < lower || val > upper) return false;
        // 找寻那个左右子树的分割点，找到第一个大于preorder[start]的值
        for (i = start + 1; i <= end; i++) {
            if (preorder[i] > preorder[start]) break;
        }
        return helper(preorder, start + 1, i - 1, lower, val) &&
                helper(preorder, i, end, val, upper);
    }
}