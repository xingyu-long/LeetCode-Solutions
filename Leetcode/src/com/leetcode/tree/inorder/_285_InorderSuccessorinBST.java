package com.leetcode.tree.inorder;

import com.leetcode.common.MainClass;
import com.leetcode.common.TreeNode;

import java.util.Stack;

public class _285_InorderSuccessorinBST {

    // inorder traversal iteratively
    public void inorder(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            System.out.println(cur.val);
            cur = cur.right;
        }
    }

    /**
     * 285.Inorder Successor in BST
     * When:2019/7/28
     * review1:2019/9/11
     * Difficulty: Medium
     * solution: 中序排序的非递归形式
     *
     * @param root
     * @param p
     * @return
     */
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

    // 利用BST的性质
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
        MainClass mainClass = new MainClass();
        TreeNode root = mainClass.stringToTreeNode(s);
        TreeNode res = inorderSuccessor2(root, new TreeNode(1));
        System.out.println(res.val);
    }
}
