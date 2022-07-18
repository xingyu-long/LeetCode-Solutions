/*
 * @Date: 07/17/2022 15:13:26
 * @LastEditTime: 07/17/2022 15:29:16
 * @Description: Shortest Path, BFS
 */
package com.leetcode.bfsANDdfs;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _2290_MinimumObstacleRemovaltoReachCorner {
    public class Point implements Comparable<Point>{
        int x;
        int y;
        int obstacle;
        
        public Point(int x, int y, int o) {
            this.x = x;
            this.y = y;
            this.obstacle = o;
        }
        
        public int compareTo(Point p) {
            return this.obstacle - p.obstacle;
        }
    }
    
    // 类似于 Dijkstra's algorithm
    // time: O(nlogn)
    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        PriorityQueue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 0, 0));
        int[][] dist = new int[m][n];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!pq.isEmpty()) {
            Point curr = pq.poll();
            if (curr.x == m - 1 && curr.y == n - 1) {
                return dist[curr.x][curr.y];
            }
            for (int[] dir : dirs) {
                int newX = curr.x + dir[0];
                int newY = curr.y + dir[1];
                if (newX < 0 || newX >= m || newY < 0 || newY >= n) continue;
                int nextRemoved = curr.obstacle;
                if (grid[newX][newY] == 1) {
                    nextRemoved++;
                }
                // may not the nearest one.
                if (nextRemoved < dist[newX][newY]) {
                    pq.offer(new Point(newX, newY, nextRemoved));
                    dist[newX][newY] = nextRemoved;
                }
            }
        }
        return -1;
    }
 }
