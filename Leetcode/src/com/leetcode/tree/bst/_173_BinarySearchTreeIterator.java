package com.leetcode.tree.bst;

import com.leetcode.common.TreeNode;

import java.util.Stack;

public class _173_BinarySearchTreeIterator {

    private Stack<TreeNode> stack;
    private TreeNode cur;

    /**
     * 173. Binary Search Tree Iterator
     * When:2019/04/25
     * review1: 2019/9/21
     * 考察点应该是 中序的iteration
     * @param root
     */
    public _173_BinarySearchTreeIterator(TreeNode root) {
        stack = new Stack<>();
        cur = root;
    }

    /**
     * test case:
     *         7
     *        / \
     *       3   15
     *           / \
     *          9  20
     *
     * cur: 7
     * push 7  stack 7
     * cur: 3
     * push 3  stack 3->7
     *
     * pop 3   stack 7
     * val = 3
     * cur = cur.right = null
     *
     * pop 7   stack 空
     * cur = 7.right = 15;
     *
     * push 15 stack 15;
     * push 9 stack 9 -> 15;
     *
     * pop 9 cur.right = null; stack ->15
     * pop 15  cur.right= 20 stack 空
     *
     * push 20  stack 20
     * pop 20
     * cur = 20.right = null
     * @return
     * the next smallest number */

    public int next() {
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        cur = stack.pop();
        int val = cur.val;
        cur = cur.right;
        return val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if (!stack.isEmpty() || cur != null) return true;
        return false;
    }
}
