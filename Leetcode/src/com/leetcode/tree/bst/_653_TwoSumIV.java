package com.leetcode.tree.bst;

import java.util.HashSet;

import com.leetcode.common.TreeNode;

/**
 * _653_TwoSumIV
 */
public class _653_TwoSumIV {

    // 其实和two sum里用hashmap一样的原理，走过就记住，然后等其中一个的差值找到。
    // time:O(n) space:O(n)
    public boolean findTarget(TreeNode root, int k) {
        HashSet<Integer> set = new HashSet<>();
        return dfs(root, set, k);
    }
    
    public boolean dfs(TreeNode root, HashSet<Integer> set, int k) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return dfs(root.left, set, k) || dfs(root.right, set, k);
    }
}