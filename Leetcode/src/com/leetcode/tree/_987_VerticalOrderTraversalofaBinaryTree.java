package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.*;

public class _987_VerticalOrderTraversalofaBinaryTree {
    // 987. Vertical Order Traversal of a Binary Tree
    HashMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new HashMap<>();
    int minX = 0, maxX = 0;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        dfs(root, 0, 0);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = minX; i <= maxX; i++) {
            List<Integer> vertical = new ArrayList<>();
            for (int key : map.get(i).keySet()) {
                while (!map.get(i).get(key).isEmpty()) {
                    vertical.add(map.get(i).get(key).poll());
                }
            }
            res.add(vertical);
        }
        return res;
    }

    public void dfs(TreeNode root, int x, int y) {
        if (root == null) return;
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        if (map.get(x) == null) {
            map.put(x, new TreeMap<>());
        }
        if (map.get(x).get(y) == null) {
            map.get(x).put(y, new PriorityQueue<>());
        }
        map.get(x).get(y).add(root.val);
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }
}
