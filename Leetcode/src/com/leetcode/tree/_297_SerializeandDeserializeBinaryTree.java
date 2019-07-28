package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _297_SerializeandDeserializeBinaryTree {

    /**
     *  297. Serialize and Deserialize Binary Tree
     *  When:2019/7/28
     *  Difficulty: Hard
     * @param root
     * @return
     */
    // 利用queue将其层次遍历出来
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return null;
        StringBuilder res = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                res.append("null ");
                continue;
            }
            res.append(node.val + " ");
            queue.offer(node.left);
            queue.offer(node.right);
        }
        return res.toString();
    }

    // 利用 queue 来记录节点并且加入非 null 的情况作为左右子节点
    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] strs = data.split(" ");
        TreeNode root = new TreeNode(Integer.parseInt(strs[0])); //先加入这个点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        for (int i = 1; i < strs.length; i++) {
            TreeNode cur = queue.poll();
            if (!strs[i].equals("null")) {
                cur.left = new TreeNode(Integer.parseInt(strs[i]));
                queue.offer(cur.left);
            }
            // 这里的 ++i需要注意 这样才能走到下一个点
            if (!strs[++i].equals("null")) {
                cur.right = new TreeNode(Integer.parseInt(strs[i]));
                queue.offer(cur.right);
            }
        }
        return root;
    }
}
