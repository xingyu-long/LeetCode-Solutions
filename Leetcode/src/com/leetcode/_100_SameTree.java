package com.leetcode;

public class _100_SameTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    /**
     * 100. Same Tree
     * When: 2019/04/15
     *
     * solution:
     * 递归两棵树的左边全部和右边全部
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
       if (p == null && q == null) return true;
       if (p == null || q == null) return false;
       if (p.val != q.val) return false;
       return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
