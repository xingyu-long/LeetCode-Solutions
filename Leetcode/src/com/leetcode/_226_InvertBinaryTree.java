package com.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class _226_InvertBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    /**
     * 226. Invert Binary Tree
     * When: 2019/04/16
     *
     * solution:
     * 算上深度优先DFS
     * Time: O(n)
     * Space: O(n) due to the "recursive"
     * 利用左右子树交换即可。
     * test case:
     *     1                1
     *    / \              / \
     *   2   3    ->      3   2
     *  / \                  / \
     * 4  null             null 4
     * 从最底部的4和null
     * 首先是一直进入其左子树 知道 4的left为null 则返回到4这个位置
     * 保存两个的值 然后替换 即4 和 null
     * 回到 2这个位置
     * 保存两个值 然后替换 2这个树和 4
     * 回到root = 1
     *
     * 相当于stack
     *
     * 广度优先 BFS 利用queue以及迭代的方法
     * test case:                           这时候3的子节点都有null
     *                                          |
     *  queue: 1         queue: 3, 2         queue: 2      queue 4    -> queue is empty
     *                     1                1                   1            1
     *                    / \              / \                 / \          / \
     *                   2   3    ->      3   2        ->     3  2         3  2
     *                  / \                  / \                / \          / \
     *                 4  null              4  null          null  4      null  4 (这时候4的子节点为null)
     * @param root
     * @return
     */
    public static TreeNode invertTree(TreeNode root) {
        // 首先判断边界条件
        if (root == null) return null;
        // 保留其原来的值 后面做交换使用
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static TreeNode invertTree2(TreeNode root) {
        // 首先判断边界条件
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            TreeNode temp = cur.left;
            cur.left = cur.right;
            cur.right = temp;
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        TreeNode right = root.right;
        right.left = new TreeNode(3);
        invertTree(root);
    }
}
