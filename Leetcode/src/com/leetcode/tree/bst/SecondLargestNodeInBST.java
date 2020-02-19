package com.leetcode.tree.bst;

import com.leetcode.common.TreeNode;

public class SecondLargestNodeInBST {
    // time:O(Height of Tree ~ logN)
    public int res = 0, count = 0;
    public int secondLargest(TreeNode root) {
        if (root == null) return -1;
        dfs(root);
        return res;
    }

    public void dfs(TreeNode root) {
        if (root == null || count > 2) return;
        dfs(root.right);
        count++;
        if (count == 2) {
            res = root.val;
        }
        dfs(root.left);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(20);
        root.right.right = new TreeNode(30);
        SecondLargestNodeInBST Bst = new SecondLargestNodeInBST();
        System.out.print(Bst.secondLargest(root));
    }
}
