package com.leetcode.tree;

import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _255_VerifyPreorderSequenceinBinarySearchTree {
    /**
     * 255. Verify Preorder Sequence in Binary Search Tree
     * When:2019/9/24
     * @param preorder
     * @return
     */
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
    public static boolean verifyPreorder3(int[] preorder) {
        return validBSTByBoundary(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE, 0, preorder.length - 1);
    }

    public static boolean validBSTByBoundary(int[] preorder, Integer min, Integer max,  int start, int end) {
        if (start > end) return true;
        int val = preorder[start];
        if (val <= min || val >= max) return false;
        int i = 0; // find the break;
        for (i = start + 1; i <= end; i++) {
            if (preorder[i] >= val) break; // 居然是这个等号的问题。一个元素的时候可以用的上。
        }
        boolean left = validBSTByBoundary(preorder, min, val, start + 1, i - 1);
        boolean right = validBSTByBoundary(preorder, val, max, i, end);
        return  left && right;
    }
    @Test
    void validate() {
        assertEquals(true, verifyPreorder3(new int[]{6,2,1,3,8,7,9}));
        assertEquals(false, verifyPreorder3(new int[]{6,2,1,3,8,9,7}));
    }
}