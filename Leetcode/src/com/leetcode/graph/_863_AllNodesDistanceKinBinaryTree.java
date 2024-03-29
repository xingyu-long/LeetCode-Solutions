package com.leetcode.graph;

import com.leetcode.common.TreeNode;

import java.util.*;

/**
 * @Date: 05/28/2020, 07/21/2020
 * @Description: Graph, BFS
 **/
public class _863_AllNodesDistanceKinBinaryTree {

    // time:O(n) space:O(n)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (root == null) {
            return new ArrayList<>();
        }
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        buildGraph(root, map);
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.offer(target.val);
        while (K > 0 && !queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curr = queue.poll();
                visited.add(curr);
                if (map.get(curr) != null) {
                    for (int adj : map.get(curr)) {
                        if (!visited.contains(adj)) {
                            queue.offer(adj);
                        }
                    }
                }
            }
            K--;
        }
        if (!queue.isEmpty()) {
            while (!queue.isEmpty()) {
                res.add(queue.poll());
            }
        }
        return res;
    }

    public void buildGraph(TreeNode root, HashMap<Integer, HashSet<Integer>> map) {
        if (root == null) {
            return;
        }
        
        map.putIfAbsent(root.val, new HashSet<>());
        
        if (root.left != null) {
            map.get(root.val).add(root.left.val);
            map.putIfAbsent(root.left.val, new HashSet<>());
            map.get(root.left.val).add(root.val);
        }
        if (root.right != null) {
            map.get(root.val).add(root.right.val);
            map.putIfAbsent(root.right.val, new HashSet<>());
            map.get(root.right.val).add(root.val);
        }
        buildGraph(root.left, map);
        buildGraph(root.right, map);
    }
}
