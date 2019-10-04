package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _112_PathSum {

    /**
     * 112. Path Sum
     * When: 2019/04/16
     * Review1:2019/7/26
     * review2:2019/9/12
     * <p>
     * <p>
     * solution:
     * 1. 跟那个Binary Tree Paths思路一致，然后计算出值
     * 2. 反着来 减去当前的值 然后左右分开
     *
     * @param root
     * @param sum
     * @return
     */

    //time:O(n) space:O(n)
    // 直接计算出
    public static boolean hasPathSum(TreeNode root, int sum) {

        if (root == null) return false; //这里是false！ 很重要
        // 这里肯定是先减去之后再在跟着验证是否找到。
        sum -= root.val;
        // 满足这个的情况下
        if (sum == 0 && root.left == null && root.right == null) return true;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    //BFS
    public boolean hasPathSum2(TreeNode root, int sum) {
        if (root == null && sum >= 0) return false;
        // 利用stack 先序遍历
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) { // 减的话不能达到保存结果的情况
            TreeNode node = stack.pop();
            if (node.left == null && node.right == null) {
                if (sum == node.val) {
                    return true;
                }
            }
            if (node.right != null) {
                stack.push(node.right);
                node.right.val += node.val;
            }
            if (node.left != null) {
                stack.push(node.left);
                node.left.val += node.val;
            }
        }
        return false;
    }
}
