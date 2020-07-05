package com.leetcode.tree;

import com.leetcode.common.TreeNode;

/**
 * @Date: 04/30/2020
 * @Description: DFS, Tree
 **/
public class CheckIfaStringIsaValidSequencefromRoottoLeavesPathinaBinaryTree {

    // time:O(len(arr)) space:O(len(arr))
    public boolean isValidSequence(TreeNode root, int[] arr) {
        if (root == null || arr == null || arr.length == 0) {
            return false;
        }
        return dfs(root, arr, 0);
    }

    public boolean dfs(TreeNode root, int[] arr, int index) {
        if (index >= arr.length) {
            return false;
        }
        if (root == null) {
            return false;
        }
        // 检查是不是为leaf节点
        if (root.left == null && root.right == null && index == arr.length - 1) {
            if (arr[index] == root.val) {
                return true;
            }
            return false;
        }

        if (root.val != arr[index]) {
            return false;
        }
        boolean left = dfs(root.left, arr, index + 1);
        boolean right = dfs(root.right, arr, index + 1);
        return left || right;
    }
}
