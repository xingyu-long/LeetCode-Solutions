/*
 * @Date: 2019-11-15 15:57:52
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-28 14:44:59
 */
package com.leetcode.tree.bst;

import com.leetcode.common.ConverterForTreeAndString;
import com.leetcode.common.TreeNode;

import java.util.Stack;

public class _285_InorderSuccessorinBST {
    // https://www.cnblogs.com/grandyang/p/5306162.html
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        boolean before = false;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            // 相当于加入一个判断的过程
            if (before) return cur;
            if (cur == p) before = true;
            cur = cur.right;
        }
        return null;
    }

    // 递归的形式
    private static TreeNode pre = null, after = null;

    public static TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        if (p == null) return null;
        helper(root, p);
        return after;
    }

    //画图演示
    public static void helper(TreeNode root, TreeNode p) {
        if (root == null) return;
        helper(root.left, p);
        if (pre == p) {
            after = root;
        }
        pre = root;
        helper(root.right, p);
    }

    // 利用BST的性质。这个不要忘记
    // logN
    public TreeNode inorderSuccessor3(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while (root != null) {
            if (root.val <= p.val) {
                root = root.right;
            } else {
                res = root;
                root = root.left;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "[3,1,4,null,2]";
        ConverterForTreeAndString converterForTreeAndString = new ConverterForTreeAndString();
        TreeNode root = converterForTreeAndString.stringToTreeNode(s);
        TreeNode res = inorderSuccessor2(root, new TreeNode(1));
        System.out.println(res.val);
    }
}
