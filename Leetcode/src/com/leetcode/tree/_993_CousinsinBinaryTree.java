package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _993_CousinsinBinaryTree {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return true;
        return getLevel(root, x, 1) == getLevel(root, y, 1) && !isSibling(root, x, y);
    }
    // DFS
    // 后序的验证是否是同一个parent。
    public boolean isSibling(TreeNode root, int x, int y) {
        if (root == null) return false;
        boolean left = isSibling(root.left, x, y);
        boolean right = isSibling(root.right, x, y);
        if (root.left != null && root.right != null) {
            if ((root.left.val == x && root.right.val == y) ||
                    (root.left.val == y && root.right.val == x)) {
                return true;
            }
        }
        return left || right;
    }

    // 利用前序来做。 基本都一样
    public boolean isSibling2(TreeNode root, int x, int y) {
        if (root == null) return false;
        if (root.left != null && root.right != null) {
            if ((root.left.val == x && root.right.val == y) ||
                    (root.left.val == y && root.right.val == x)) {
                return true;
            }
        }
        boolean left = isSibling2(root.left, x, y);
        boolean right = isSibling2(root.right, x, y);
        return left || right;
    }

    public int getLevel(TreeNode root, int target, int level) {
        if (root == null) return 0;
        if (root.val == target) return level;
        int count = getLevel(root.left, target, level + 1);
        // 中间这个步骤太帅了！！！
        if (count != 0) return count;
        count = getLevel(root.right, target, level + 1);
        return count;
    }


    // BFS.
    public boolean isCousins3(TreeNode root, int x, int y) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isExistX = false;
            boolean isExistY = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val == x) isExistX = true;
                if (node.val == y) isExistY = true;
                // 每次check是否为sibling。
                if (node.left != null && node.right != null) {
                    if (node.left.val == x && node.right.val == y) return false;
                    if (node.left.val == y && node.right.val == x) return false;
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            if (isExistX && isExistY) return true;
        }
        return false;
    }
}
