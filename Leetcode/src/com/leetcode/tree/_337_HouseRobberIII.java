package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class _337_HouseRobberIII {
    // ? 如何做到跳跃
    /**
     * 337. House Robber III
     * When:2019/7/27
     * Difficulty: Medium
     * 依然感觉比较难
     * 其中比较的原则就是 当前root.val + left.left 或者left.right 以及 left+right的比较
     * @param root
     * @return
     */
    // 解释 https://www.cnblogs.com/grandyang/p/5275096.html
    public static int rob(TreeNode root) {
        if (root == null) return 0;
        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }
        return Math.max(val + root.val, rob(root.left) + rob(root.right));
    }

    // 利用 HashMap 存储中间值减少运算 利用这个来了解运算过程更加清晰
    public static int rob2(TreeNode root) {
        if (root == null) return 0;
        HashMap<TreeNode, Integer> map = new HashMap<>();
        return dfs(root, map);
    }

    public static int dfs(TreeNode root, HashMap<TreeNode, Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);
        int val = 0;
        if (root.left != null) {
            val += dfs(root.left.left, map) + dfs(root.left.right, map);
        }
        if (root.right != null) {
            val += dfs(root.right.left, map) + dfs(root.right.right, map);
        }
        val = Math.max(val + root.val, dfs(root.left, map) + dfs(root.right, map));
        map.put(root, val);
        return val;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
//        root.left = new TreeNode(2);
//        root.right = new TreeNode(3);
//        root.left.right = new TreeNode(3);
//        root.right.right = new TreeNode(1);
        root.left = new TreeNode(1);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        rob2(root);
    }
}
