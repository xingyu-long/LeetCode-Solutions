package com.leetcode.bfs_and_dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2019/7/25, 05/24/2020
 * @Description: BFS
 **/
public class _286_WallsandGates {
    //time:(m * n) space:(m * n)？？
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) {
            return;
        }
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    public void dfs(int[][] rooms, int i, int j, int dis) {
        if (i < 0 || i >= rooms.length
                || j < 0 || j >= rooms[0].length || rooms[i][j] < dis) {
            return;
        }
        rooms[i][j] = dis;
        dfs(rooms, i - 1, j, dis + 1);
        dfs(rooms, i + 1, j, dis + 1);
        dfs(rooms, i, j - 1, dis + 1);
        dfs(rooms, i, j + 1, dis + 1);
    }

    // BFS，这个比DFS好，因为一定保持m * n的访问
    // time:O(m * n)
    public void wallsAndGates2(int[][] rooms) {
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0) return;
        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int[] dir : dirs) {
                int x = curr[0] + dir[0], y = curr[1] + dir[1];
                if (x < 0 || x >= rooms.length || y < 0 || y >= rooms[0].length) continue;
                if (rooms[x][y] != Integer.MAX_VALUE) continue;
                rooms[x][y] = rooms[curr[0]][curr[1]] + 1;
                queue.offer(new int[]{x, y});
            }
        }
    }
}