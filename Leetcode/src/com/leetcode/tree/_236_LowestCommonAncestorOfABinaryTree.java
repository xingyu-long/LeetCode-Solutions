package com.leetcode.tree;

import com.leetcode.lib.TreeNode;

public class _236_LowestCommonAncestorOfABinaryTree {

    TreeNode res = null;

    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     * When: 2019/04/22
     *
     * solution:
     * 设置left, mid, right三个变量
     * 只要当前有一个等于p或者q 则就会有true 当三个 加起来 > 2这个时候就已经是该节点了
     *
     * Test case:
     *  p = 4, q = 10
     *           5
     *          / \
     *         4   9
     *        /   / \
     *       2   6  10
     *
     *  5 ---> 4 (found)---> 2
     *  then left = 1;
     *  5 ---> 9 ---> 6
     *  backtrack
     *  6 ---> 9
     *  5 ---> 9 ---> 10 (found)
     *  right = 1
     *  只有5这个点的时候left和right = 1 满足
     *  return 5
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return res;
    }

    public boolean helper(TreeNode current, TreeNode p, TreeNode q) {
        if (current == null) return false;

        int left = helper(current.left, p, q) ? 1 : 0;

        int right = helper(current.right, p, q) ? 1 : 0;

        int mid = (current == p || current  == q) ? 1 : 0;

        if (left + mid + right >= 2) res = current;

        return (left + mid + right > 0);
    }
}
