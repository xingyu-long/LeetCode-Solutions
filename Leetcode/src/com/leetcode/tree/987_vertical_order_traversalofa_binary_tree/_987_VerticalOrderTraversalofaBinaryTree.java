/*
 * @Date: 01/16/2020 11:14:56
 * @LastEditTime: 08/02/2022 21:54:46
 * @Description: Tree, PQ & TreeMap
 */
package com.leetcode.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import com.leetcode.common.TreeNode;

public class _987_VerticalOrderTraversalofaBinaryTree {
    // 987. Vertical Order Traversal of a Binary Tree
    // https://www.youtube.com/watch?v=kqHNP6NTzME
    Map<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map;
    int minX, maxX;
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        map = new HashMap<>();
        minX = maxX = 0;
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
        map.putIfAbsent(x, new TreeMap<>());
        map.get(x).putIfAbsent(y, new PriorityQueue<>());
        map.get(x).get(y).add(root.val);
        // y是用treemap保持从上到下的顺序，所有这里用了升序的treemap。
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }
}
