/*
 * @Date: 2019-11-16 10:32:40
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-30 21:54:49
 */
package com.leetcode.tree;

import java.util.Stack;

public class _255_VerifyPreorderSequenceinBinarySearchTree {
    // 利用bst性质来做
    // 还是需要多记忆
    // time:O(n) space:O(n)
    public static boolean verifyPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) return true;
        Stack<Integer> stack = new Stack<>();
        int min = Integer.MIN_VALUE;
        for (int val : preorder) {
            if (val < min) return false;
            while (!stack.isEmpty() && val > stack.peek()) { // 相当于把小于的左半部分全都删除。
                min = stack.pop();
            }
            stack.push(val);
        }
        return true;
    }

    // time:O(n) space:O(1)
    // 和上面一样的道理，有点像利用array写的stack。
    public static boolean verifyPreorder2(int[] preorder) {
        int min = Integer.MIN_VALUE;
        int index = -1;
        for (int val : preorder) {
            if (val < min) return false;
            while (index >= 0 && val > preorder[index]) min = preorder[index--];
            preorder[++index] = val;
        }
        return true;
    }
    // 还是模仿reconstruct树那个思路
    // time: O(nlogn)
    public static boolean verifyPreorder3(int[] preorder) {
        return validBSTByBoundary(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, preorder.length - 1);
    }

    public static boolean validBSTByBoundary(int[] preorder, Integer min, Integer max,  int start, int end) {
        if (start > end) return true;
        int val = preorder[start];
        if (val < min || val > max) return false;
        int i = 0; // find the break;
        for (i = start + 1; i <= end; i++) {
            if (preorder[i] > val) break; 
        }
        // 防止[2, 1]的情况 这里用i作为参数，这样才可以取到最后一位
        boolean left = validBSTByBoundary(preorder, min, val, start + 1, i - 1);
        boolean right = validBSTByBoundary(preorder, val, max, i, end);
        return  left && right;
    }
}