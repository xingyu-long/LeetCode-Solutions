package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _112_PathSum {


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 112. Path Sum
     * When: 2019/04/16
     *
     * solution:
     * 1. 跟那个Binary Tree Paths思路一致，然后计算出值
     * 2. 反着来 减去当前的值 然后左右分开
     *
     * @param root
     * @param sum
     * @return
     */
    public static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        boolean res = false;
        List<Integer> list = new ArrayList<>();
        helper(0, root, list);
        res = list.contains(sum);
        return res;
    }

    public static void helper(int sum, TreeNode root, List<Integer> list) {
        if (root == null) return;
        sum += root.val;

        if(root.left == null && root.right == null) {
            list.add(sum);
        }

        helper(sum, root.left, list);
        helper(sum, root.right, list);
    }

    public static boolean hasPathSum2(TreeNode root, int sum) {

        if (root == null) return false;
        sum -= root.val;
        // 满足这个的情况下
        if (sum == 0 && root.left == null && root.right == null) return true;
        return hasPathSum2(root.left, sum) || hasPathSum2(root.right, sum);
    }
}
