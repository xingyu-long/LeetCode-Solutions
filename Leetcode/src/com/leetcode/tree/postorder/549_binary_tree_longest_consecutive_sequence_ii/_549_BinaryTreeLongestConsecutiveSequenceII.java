package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

public class _549_BinaryTreeLongestConsecutiveSequenceII {
    // 记录每一个点的increase以及decrease的数目
    class Result {
        TreeNode node;
        int inc;
        int dec;

        public Result(TreeNode node, int inc, int dec) {
            this.node = node;
            this.inc = inc;
            this.dec = dec;
        }
    }

    public int res;
    public int longestConsecutive(TreeNode root) {
        // how to show child-parent-child order?
        // 有想到，但是没有想到用inc和dec的数目来做。
        res = 0;
        dfs(root);
        return res;
    }

    public Result dfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        Result left = dfs(root.left);
        Result right = dfs(root.right);

        Result curr = new Result(root, 1, 1);

        if (left != null) {
            if (left.node.val - 1 == root.val) {
                curr.dec = Math.max(curr.dec, left.dec + 1);
            } else if (left.node.val + 1 == root.val) {
                curr.inc = Math.max(curr.inc, left.inc + 1);
            }
        }

        if (right != null) {
            if (right.node.val - 1 == root.val) {
                curr.dec = Math.max(curr.dec, right.dec + 1);
            } else if (right.node.val + 1 == root.val) {
                curr.inc = Math.max(curr.inc, right.inc + 1);
            }
        }
        // System.out.println("curr = " + root.val + " curr.inc" + curr.inc + " curr.dec = " + curr.dec);
        res = Math.max(res, curr.inc + curr.dec - 1);
        return curr;
    }
}
