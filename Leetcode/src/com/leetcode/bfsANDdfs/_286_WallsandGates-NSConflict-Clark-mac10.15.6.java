package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2019/7/25, 05/24/2020
 * @Description: BFS
 **/
public class _286_WallsandGates {

    /**
     * 286. Walls and Gates When:2019/7/25 Difficulty: Medium review1:10/31/2019
     *
     * @param rooms
     */
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

<<<<<<< HEAD
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // 能不能用一个BFS遍历这些？？
=======
    // 这里的visited需要和numer of island比较 这里的visited只是针对每次情况，而number of island需要全程一个visited数组
    public static void bfs(int[][] board, int row, int col) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        Queue<int[]> queue = new LinkedList<>();
        visited[row][col] = true;
        queue.offer(new int[]{row, col});
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
>>>>>>> df5c759ff1fb4db19421e4327667df71bb0e1952
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

<<<<<<< HEAD
=======
    public static boolean isValid(int[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length
            || board[row][col] == -1) {
            return false;
        }
        return true;
    }

>>>>>>> df5c759ff1fb4db19421e4327667df71bb0e1952
}