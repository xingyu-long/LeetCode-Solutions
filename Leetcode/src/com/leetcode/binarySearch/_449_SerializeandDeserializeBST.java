package com.leetcode.binarySearch;

import com.leetcode.common.TreeNode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class _449_SerializeandDeserializeBST {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        encode(root, sb);
        return sb.toString();
    }

    public void encode(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
            return;
        }
        sb.append(root.val).append(",");
        encode(root.left, sb);
        encode(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(",")));
        return decode(queue);
    }

    public TreeNode decode(Queue<String> queue) {
        String str = queue.poll();
        if (str.equals("#")) return null;
        int val = Integer.parseInt(str);
        TreeNode root = new TreeNode(val);
        root.left = decode(queue);
        root.right = decode(queue);
        return root;
    }
}
