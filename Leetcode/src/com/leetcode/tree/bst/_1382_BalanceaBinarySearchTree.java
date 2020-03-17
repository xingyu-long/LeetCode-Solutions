package com.leetcode.tree.bst;

import java.util.ArrayList;
import java.util.List;
import com.leetcode.common.TreeNode;

public class _1382_BalanceaBinarySearchTree {
    List<Integer> list;
    
    /**
     * When:03/15/2020
     * @param root
     * @return
     */
    // time:O(n) space:O(n)
    public TreeNode balanceBST(TreeNode root) {
        if (root == null) return null;
        list = new ArrayList<>();
        dfs(root);
        return build(list, 0, list.size() - 1);
    }
    
    public void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        list.add(root.val);
        dfs(root.right);
    }
    
    public TreeNode build(List<Integer> list, int left, int right) {
        if (left > right) return null; //注意这个边界条件
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(list.get(mid));
        root.left = build(list, left, mid - 1);
        root.right = build(list, mid + 1, right);
        return root;
    }
    
}