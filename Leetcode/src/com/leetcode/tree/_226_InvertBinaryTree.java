package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _226_InvertBinaryTree {

    /**
     *  226. Invert Binary Tree
     *  When: 2019/04/16
     *  Review1:2019/7/26
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
    // time:
    public static TreeNode invertTree(TreeNode root) {
        // 首先判断边界条件
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        invertTree(root.left);
        invertTree(root.right);

        return root;
    }
    // BFS
    // time:O(n) space:O(n)
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
}
