package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _104_MaximumDepthOfBinaryTree {

    /**
     *  104. Maximum Depth of Binary Tree
     *  When: 2019/04/18
     *  Review1: 2019/7/24
     *
     * solution：
     * 1. 可以使用先序遍历来做
     * 2. 跟111的思路一模一样 但是不需要里面的if 因为都是math.max
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    // time:O(n) space:O(n)
    public int maxDepth2(TreeNode root) {
        // preorder
        int res = helper(0, root);
        return res;
    }
    public int helper(int level, TreeNode root) {
        if (root == null) {
            return level; //返回当前层的level数 相当于走到了null这个节点的时候，层数如果是1开始计数就刚刚是前一层的level数
        }
//        System.out.println("level = " + level + "root" +root.val);
        int left = helper(level + 1, root.left);
        int right = helper(level + 1, root.right);
        return Math.max(left, right);
    }
}
