/*
 * @Date: 2019-09-11 15:44:00
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-21 14:14:47
 */
package com.leetcode.tree.inorder;

import com.leetcode.common.ConverterForTreeAndString;
import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _94_BinaryTreeInorderTraversal {
    // 最普通的中排序，并且这里的输入是root节点而不是数组所以自己不用构造才对。
    // 中序输出，则就是左遍历到底 输出值 然后中间值 再右遍历 再输出值
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root);
        return res;
    }

    public static void helper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        helper(res, root.left);
        res.add(root.val);
        helper(res, root.right);
    }
    // stack
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (!stack.isEmpty() || curr != null) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    // Morris, space:O(1)
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode temp = curr.left;
                while (temp != null && temp.right != null && temp.right != curr) {
                    temp = temp.right;
                }
                if (temp.right == null) {
                    // build the connection
                    temp.right = curr;
                    curr = curr.left;
                } else {
                    // cut the connection
                    res.add(curr.val);
                    temp.right = null;
                    curr = curr.right;
                }
            }
        }
        return res;
    }
}
