package com.new_grad.amazon;

import com.leetcode.common.TreeNode;

public class _1120_MaximumAverageSubtree {
    class Node {
        double sum;
        double count;

        public Node() {
        }

        public Node(double s, double c) {
            sum = s;
            count = c;
        }
    }

    double res;

    public double maximumAverageSubtree(TreeNode root) {
        res = Integer.MIN_VALUE;
        dfs(root);
        return res;
    }

    public Node dfs(TreeNode root) {
        if (root == null) {
            return new Node(0, 0);
        }

        Node left = dfs(root.left);
        Node right = dfs(root.right);

        Node ret = new Node();
        ret.sum = left.sum + right.sum + root.val;
        ret.count = left.count + right.count + 1;
        res = Math.max(res, ret.sum / ret.count);
        // 对于多叉树而言
        // 需要遍历其中的Node.child即可。
        return ret;
    }
}
