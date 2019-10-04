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
}