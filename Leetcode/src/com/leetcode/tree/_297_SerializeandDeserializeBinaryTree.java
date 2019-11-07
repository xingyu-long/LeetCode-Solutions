package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _297_SerializeandDeserializeBinaryTree {

    /**
     * 297. Serialize and Deserialize Binary Tree
     * When:2019/7/28
     * review1:11/4/2019
     * Difficulty: Hard
     *
     * @param root
     * @return
     */
    // 利用queue将其层次遍历出来
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        Queue<TreeNode> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        queue.offer(root);
        sb.append(root.val);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left == null) {
                    sb.append(",null");
                } else if (cur.left != null) {
                    sb.append("," + cur.left.val);
                    queue.offer(cur.left);
                }
                if (cur.right == null) {
                    sb.append(",null");
                } else if (cur.right != null) {
                    sb.append("," + cur.right.val);
                    queue.offer(cur.right);
                }
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] strs = data.split(",");
        TreeNode head = new TreeNode(Integer.parseInt(strs[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        int index = 1;
        queue.offer(head);
        while (!queue.isEmpty() && index < strs.length) {
            TreeNode node = queue.poll();
            if (!strs[index].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(strs[index]));
                queue.offer(node.left);
            }
            index++;
            if (!strs[index].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(strs[index]));
                queue.offer(node.right);
            }
            index++;
        }
        return head;
    }

    public static void main(String[] args) {
        deserialize("1,3,null,4,null,null");
    }
}
