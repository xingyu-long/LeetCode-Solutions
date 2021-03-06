package com.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

public class _1361_ValidateBinaryTreeNodes {
    // 这个其实是BFS + visited来模拟的情况 虽然能过，但是这题其实出的不好，要是其他点作为root呢？
    // 利用0作为root，同时记录个数，如果模拟完之后
    // 没有 return false （说明没有重复访问的情况）就得看看当前的树的个数与总的相同，不然的话就说明有另外的树，
    // 表明这个就不是有效的树
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        // 利用邻接表来
        // 主要是如何处理多个root的情况？
        boolean[] visited = new boolean[n];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        // 利用0来构建，
        while (!queue.isEmpty()) {
            count++;
            int curr = queue.poll();
            if (visited[curr]) return false;
            visited[curr] = true;
            if (leftChild[curr] != -1) queue.offer(leftChild[curr]);
            if (rightChild[curr] != -1) queue.offer(rightChild[curr]);
        }
        return n == count;
    }

    // 通过indegree来做
    public boolean validateBinaryTreeNodes2(int n, int[] leftChild, int[] rightChild) {
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            // 这里用的很妙
            if (leftChild[i] != -1 && indegree[leftChild[i]]++ == 1) return false;
            if (rightChild[i] != -1 && indegree[rightChild[i]]++ == 1) return false;
        }
        int root = -1;
        // check if it is mulitple roots.
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                if (root != -1) return false;
                root = i;
            }
        }
        boolean[] visited = new boolean[n];
        // 这里是指有一个root开始！
        return root != -1 && dfs(root, leftChild, rightChild, visited);
    }

    // 用来看是否每个点都只是被访问一次。
    public boolean dfs(int root, int[] left, int[] right, boolean[] visited) {
        if (root == -1) return true;// base case.
        if (visited[root]) return false;
        visited[root] = true;
        return dfs(left[root], left, right, visited) 
        && dfs(right[root], left, right, visited);
    }
}
