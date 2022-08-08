/*
 * @Date: 07/19/2022 10:47:12
 * @LastEditTime: 07/19/2022 10:48:23
 * @Description: BFS, PQ
 */
package com.leetcode.bfs_and_dfs;

import java.util.PriorityQueue;

public class _778_SwiminRisingWater {
    public class Node implements Comparable<Node> {
        int x;
        int y;
        int max;
        int val;

        public Node(int x, int y, int val, int max) {
            this.x = x;
            this.y = y;
            this.val = val;
            this.max = max;
        }

        public int compareTo(Node o) {
            return this.val - o.val;
        }
    }

    // time: O(m*n log (m * n))
    public int swimInWater(int[][] grid) {
        // priority queue?
        // max value of min path
        int m = grid.length, n = grid[0].length;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, grid[0][0], grid[0][0]));
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        int[][] dirs = { { 0, 1 }, { 1, 0 }, { -1, 0 }, { 0, -1 } };
        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            if (curr.x == m - 1 && curr.y == n - 1) {
                return curr.max;
            }
            for (int[] dir : dirs) {
                int newX = curr.x + dir[0];
                int newY = curr.y + dir[1];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n) {
                    continue;
                }
                if (visited[newX][newY])
                    continue;
                visited[newX][newY] = true;
                int currMax = Math.max(curr.max, grid[newX][newY]);
                pq.offer(new Node(newX, newY, grid[newX][newY], currMax));
            }
        }
        return -1;
    }
}
