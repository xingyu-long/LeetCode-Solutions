package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _100_SameTree {

    /**
     *  100. Same Tree
     *  When: 2019/04/15
     *  Review1:2019/7/25
     *
     * solution:
     * 递归两棵树的左边全部和右边全部
     * @param p
     * @param q
     * @return
     */
    // time:O(n) space: best O(lgN) worse O(n)
    public boolean isSameTree(TreeNode p, TreeNode q) {
       if (p == null && q == null) return true; // 主要依靠这个返回
       if (p == null || q == null) return false;
       if (p.val != q.val) return false; // 之前写错成了 p == q return true
       return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
