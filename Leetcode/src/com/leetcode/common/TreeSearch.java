package com.leetcode.common;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class TreeSearch {

    /**
     * 深度优先搜索 利用stack 并且优先push 右边
     * @param root
     */
    public static void DepthFirstSearch(TreeNode root) {
        if (root == null) {
            System.out.println("Empty Tree!!!");
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val);
            //这里粗心了！！！！应该是当前node的左右子树！！！
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
            System.out.print(" ");
        }
    }

    /**
     * 广度优先搜索 利用queue
     *
     * @param root
     */
    public static void BreathFirstSearch(TreeNode root) {
        if (root == null) {
            System.out.println("Empty Tree!!!");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val);
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
            System.out.print(" ");
        }
    }

    public static void main(String[] args){
        String s = "[1,2,3,4,5,6,7,8,9]";
        MainClass mainClass = new MainClass();
        TreeNode root = mainClass.stringToTreeNode(s);
//        BreathFirstSearch(root);
        System.out.println();
        DepthFirstSearch(root);
    }
}
