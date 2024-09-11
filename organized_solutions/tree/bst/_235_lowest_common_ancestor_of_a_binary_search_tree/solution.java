/*
 * @Date: 2019-09-20 17:40:27
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-26 14:24:28
 */
package com.leetcode.tree.bst;

import com.leetcode.common.TreeNode;

public class _235_LowestCommonAncestorOfABinarySearchTree {
    // 递归的方法 (recursive)
    // 利用其二叉搜索树性质
    // time: N space: N 因为它不是balanced BST
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
