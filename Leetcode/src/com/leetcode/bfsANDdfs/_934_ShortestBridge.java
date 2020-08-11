package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 06/17/2020
 * @Description: TODO
 **/
public class _934_ShortestBridge {

    // DFS + BFS
    // time:O(m * n) space:O(m * n)
    public int shortestBridge(int[][] A) {
        /*
        [[0,1,0],
         [0,0,0],
         [0,0,1]]

         [[1,1,1,1,1],
         [1,0,0,0,1],
         [1,0,1,0,1],
         [1,0,0,0,1],
         [1,1,1,1,1]]

          找到一个岛，然后往里面同时找
        */
        // DFS + BFS
        if (A == null || A.length == 0 || A[0] == null || A[0].length == 0) {
            return 0;
        }
        int m = A.length, n = A[0].length;
        boolean[][] visited = new boolean[m][n];
        boolean found = false;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            if (found) {
                break;
            }
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    dfs(queue, A, i, j, visited);
                    found = true;
                    break;
                }
            }
        }
        return bfs(queue, A, visited);
    }

    private void dfs(Queue<int[]> queue, int[][] A, int i, int j, boolean[][] visited) {
        if (i < 0 || i >= A.length || j < 0 || j >= A[0].length) {
            return;
        }
        if (visited[i][j] || A[i][j] != 1) {
            return;
        }
        visited[i][j] = true;
        queue.offer(new int[]{i, j});
        dfs(queue, A, i + 1, j, visited);
        dfs(queue, A, i - 1, j, visited);
        dfs(queue, A, i, j + 1, visited);
        dfs(queue, A, i, j - 1, visited);
    }

    private int bfs(Queue<int[]> queue, int[][] A, boolean[][] visited) {
        int steps = 0;
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int currX = curr[0] + dir[0];
                    int currY = curr[1] + dir[1];
                    if (currX < 0 || currX >= A.length || currY < 0 ||
                        currY >= A[0].length || visited[currX][currY]) {
                        continue;
                    }
                    if (A[currX][currY] == 1) {
                        return steps;
                    }
                    queue.offer(new int[]{currX, currY});
                    visited[currX][currY] = true;
                }
            }
            steps++;
        }
        return steps;
    }
}
