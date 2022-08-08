/*
 * @Date: 12/28/2019 13:32:43
 * @LastEditTime: 04/11/2021 08:51:45
 * @Description: BFS
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _1302_DeepestLeavesSum {
    // time:O(n) space:O(n)
    // use the global variable.
    public int res = 0;
    public int deepestLeavesSum(TreeNode root) {
        if (root == null) return 0;
        int depth = maxDis(root);
        helper(root, 1, depth);
        return res;
    }

    public void helper(TreeNode root, int level, int max) {
        if (root == null) return;
        if (level == max) res += root.val;
        helper(root.left, level + 1, max);
        helper(root.right, level + 1, max);
    }

    public int helperWithReturn(TreeNode root, int level) {
        if (root == null) return 0;
        if (level == 1) return root.val;// 到了最深的地方
        int left = helperWithReturn(root.left,level - 1);
        int right = helperWithReturn(root.right, level - 1);
        return left + right;
    }

    public int maxDis(TreeNode root) {
        if (root == null) return 0;
        int left = maxDis(root.left);
        int right = maxDis(root.right);
        return Math.max(left, right) + 1;
    }

    // BFS更加的直观  time:O(n) space:O(n)
    // 需要在poll出点的时候相加而不是在左右子树，这个就不是再算当前层的总和了。
    public int deepestLeavesSum2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int sum = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            sum = 0; // calculate each level.
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return sum;
    }
}
