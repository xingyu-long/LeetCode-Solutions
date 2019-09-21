package com.leetcode.tree.bst;

import com.leetcode.common.TreeNode;

public class _235_LowestCommonAncestorOfABinarySearchTree {

    /**
     * 235. Lowest Common Ancestor of a Binary Search Tree
     * When: 2019/04/23
     * <p>
     * solution:
     * 递归的方法 (recursive)
     * 利用其二叉搜索树性质
     *
     * time: O(n)
     * space: O(n)
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 利用其二叉搜索树的性质（即左边小于右边 并且子树也符合这个性质）
        if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }

    // 迭代的方式 iterative
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = root;
        while (node != null) {
            if (node.val > p.val && node.val > q.val) {
                node = node.left;
            } else if (node.val < p.val && node.val < q.val) {
                node = node.right;
            } else {
                return node;
            }
        }
        return null;
    }
}
