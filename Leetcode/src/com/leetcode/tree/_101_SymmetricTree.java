package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _101_SymmetricTree {

    /**
     *  101. Symmetric Tree
     *  When: 2019/04/15
     *  Review1:2019/7/26
     *  Difficulty: Medium

    solution:
        1. 主要思路利用递归。对称的比较
        2. 使用迭代的形式。 利用队列，分别插入左边的这棵树 和 右边的这棵树的 每一个对应节点
        这里面相当于是 两个一样的树 然后逐个比较
     * time: O(n)
     * space: O(n)
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        // 基本思想与 same tree一致 使用递归（但是这里比较的是a树的左边和b的右边）
        return isMirror(root, root);
    }

    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        if (t1.val != t2.val) return false;
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    //BFS
    public boolean isSymmetric2(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }

    public boolean isSymmetric3(TreeNode root) {
        if (root == null) return true;
        return helper(root.left, root.right);
    }

    public boolean helper(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;
        if (left.val != right.val) return false;
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
}