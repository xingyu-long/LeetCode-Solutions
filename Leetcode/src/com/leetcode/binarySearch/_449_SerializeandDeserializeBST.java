package com.leetcode.binarySearch;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class _449_SerializeandDeserializeBST {

    /**
     *  449. Serialize and Deserialize BST
     *  When: 2019/7/19
     *  Difficulty: Medium
     *
     * @param root
     * @return
     */

    // 使用前序遍历然后加入“ ”分割
    public static String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder res = new StringBuilder();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.append(cur.val + " ");
            if (cur.right != null) stack.push(cur.right);
            if (cur.left != null) stack.push(cur.left);
        }
        return res.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == "") return null;
        String[] str = data.split(" ");
        Queue<TreeNode> queue = new LinkedList<>();
        for (String s : str) {
            queue.offer(new TreeNode(Integer.parseInt(s)));
        }
        TreeNode root = queue.poll();
        System.out.println(queue.size());
        while (!queue.isEmpty()){
            System.out.println("in:"+queue.peek());
            bstInsert(root, queue.poll());
        }
        return root;
    }

    private static void bstInsert(TreeNode root, TreeNode newNode) {
        if (newNode.val < root.val) {
            // 左边
            if (root.left != null) {
                bstInsert(root.left, newNode);
            } else {
                root.left = newNode;
            }
        } else {
            //右边
            if (root.right != null) {
                bstInsert(root.right, newNode);
            } else {
                root.right = newNode;
            }
        }
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        System.out.println(deserialize(serialize(root)).right.val);
    }
}
