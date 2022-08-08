package com.leetcode.tree.bst;

import com.leetcode.common.TreeNode;

import java.util.Stack;
/**
 * @Date: 05/13/2020
 * @Description: Tree, Inorder, Stack
 **/
public class _173_BinarySearchTreeIterator {

    private Stack<TreeNode> stack;
    private TreeNode curr;

    public _173_BinarySearchTreeIterator(TreeNode root) {
        stack = new Stack<>();
        curr = root;
    }

    public int next() {
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
        curr = stack.pop();
        int val = curr.val;
        curr = curr.right;
        return val;
    }

    public boolean hasNext() {
        return (!stack.isEmpty() || curr != null);
    }
}
