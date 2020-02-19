package com.leetcode.common;

import java.util.LinkedList;
import java.util.Queue;

public class ConverterForTreeAndString {

    /**
     * [1,2,3,4,5] ->
     *     1
     *    / \
     *   2  3
     *  / \
     * 4  5
     * @param input
     * @return
     */
    public TreeNode stringToTreeNode(String input) {
        input = input.trim(); // 删除无关的空格
        input = input.substring(1, input.length() - 1); //得到里面的字符

        if (input.length() == 0) {
            return null;
        }

        String[] parts = input.split(",");
        String item = parts[0];
        TreeNode root = new TreeNode(Integer.parseInt(item));
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        // 利用队列的进去性质 但是root以及后面的添加一直有效
        nodeQueue.add(root);

        int index = 1;
        // 利用队列 进行左右子树的添加
        while (!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.remove();
            //添加左边子树
            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();

            if (!item.equals("null")) {
               int left = Integer.parseInt(item);
               node.left = new TreeNode(left);
               nodeQueue.add(node.left);
            }
            // 添加右子树
            if (index == parts.length) {
                break;
            }

            item = parts[index++];
            item = item.trim();

            if (!item.equals("null")) {
                int right = Integer.parseInt(item);
                node.right = new TreeNode(right);
                nodeQueue.add(node.right);
            }
        }
        return root;
    }


    /**
     * 相反，从tree 变为string
     * @param root
     * @return
     */
    public String treeNodeToString(TreeNode root) {
        if (root == null) {
            return "[]";
        }

        String output = "";
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);

        while (!nodeQueue.isEmpty()) {
            TreeNode current = nodeQueue.remove();

            if (current == null) {
                continue;
            }
            if (current == null) {
                output += "null, ";
                continue;
            }
            output += String.valueOf(current.val) + ", ";
            nodeQueue.add(current.left);
            nodeQueue.add(current.right);
        }
        return "[" + output.substring(0, output.length() - 2) + "]";
    }


}