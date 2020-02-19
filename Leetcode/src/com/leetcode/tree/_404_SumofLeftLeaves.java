package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _404_SumofLeftLeaves {

    public class MarkNode {
        TreeNode node;
        String dir;
        public MarkNode(TreeNode node) {
            this.node = node;
            dir = "";
        }
    }
    // BFS
    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) return 0;
        Queue<MarkNode> queue = new LinkedList<>();
        int res = 0;
        MarkNode mRoot = new MarkNode(root);
        queue.offer(mRoot);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                MarkNode cur = queue.poll();
                if (cur.node.left == null && cur.node.right == null && cur.dir.equals("left")) {
                    res += cur.node.val;
                }
                if (cur.node.left != null) {
                    MarkNode left = new MarkNode(cur.node.left);
                    left.dir = "left";
                    queue.offer(left);
                }
                if (cur.node.right != null) {
                    MarkNode right = new MarkNode(cur.node.right);
                    right.dir = "right";
                    queue.offer(right);
                }
            }
        }
        return res;
    }

    // 不用新建对象对象的BFS
    // 关注其left.left 以及left.right是否为空，这就表明在左叶子节点。
    // time:O(n) space:O(n)
    public int sumOfLeftLeaves2(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        int res = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    if (cur.left.left == null && cur.left.right == null) {
                        res += cur.left.val;
                    }
                    queue.offer(cur.left);
                }

                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        return res;
    }
    // DFS
    // 相当于设置flag标志然后后面是post order递归到根节点。前面也会有返回操作。
    public int sumOfLeftLeaves3(TreeNode root) {
        return helper(root, false);
    }

    public int helper(TreeNode root, Boolean isLeft) {
        if (root == null) return 0;
        if (root.left == null && root.right == null && isLeft) {
            return root.val;
        }
        int left = helper(root.left, true);
        int right = helper(root.right, false);
        return left + right;
    }
}
