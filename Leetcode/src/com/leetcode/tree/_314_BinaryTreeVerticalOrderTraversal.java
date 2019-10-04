package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _314_BinaryTreeVerticalOrderTraversal {

    /**
     * 314. Binary Tree Vertical Order Traversal
     * When:2019/9/25
     * 利用左子树 -1 右子树 + 1这样的方式来做，并且暴力两个queue。
     * @param root
     * @return
     */
    private static int min = Integer.MAX_VALUE;
    private static int max = Integer.MIN_VALUE;

    public static List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return  res;
        }
        helper(root, 0);
        for (int i = min; i <= max; i++) {
            res.add(new ArrayList<>());
        }
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> idxQueue = new LinkedList<>();
        queue.offer(root);
        idxQueue.offer(0 - min); // 计算最小到0的位置，这样存储到结果的位置，就是在 abs(min)的位置
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            int index = idxQueue.poll();
            res.get(index).add(cur.val);
            if (cur.left != null) {
                queue.offer(cur.left);
                idxQueue.offer(index - 1);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
                idxQueue.offer(index + 1);
            }
        }
        return res;
    }

    public static void helper(TreeNode root, int index) {
        if (root == null) return;
        min = Math.min(min, index);
        max = Math.max(max, index);
        helper(root.left, index - 1);
        helper(root.right, index + 1);
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(7);
        System.out.println(verticalOrder(root));
    }
}