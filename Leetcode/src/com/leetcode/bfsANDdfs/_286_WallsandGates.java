package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

public class    _286_WallsandGates {
    /**
     *  286. Walls and Gates
     *  When:2019/7/25
     *  Difficulty: Medium
     *  review1:10/31/2019
     * @param rooms
     */
    //time:(m * n) space:(m * n)？？
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null || rooms.length == 0) return;
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
        dfs(rooms,i - 1,j,dis + 1);
        dfs(rooms,i + 1,j,dis + 1);
        dfs(rooms,i,j - 1,dis + 1);
        dfs(rooms,i,j + 1,dis + 1);
    }


    //bfs
    public static void wallsAndGates2(int[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
            return;
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 0) {
                    bfs(board, i, j);
                }
            }
        }
    }

    // 这里的visited需要和numer of island比较 这里的visited只是针对每次情况，而number of island需要全程一个visited数组
    public static void bfs(int[][] board, int row, int col) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        Queue<int[]> queue = new LinkedList<>();
        visited[row][col] = true;
        queue.offer(new int[]{row, col});
        int[][] dirs = new int[][]{{-1,0}, {0, -1}, {1, 0}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] point = queue.poll();
            for (int[] dir : dirs) {
                int x = point[0];
                int y = point[1];
                int curVal = board[x][y];
                if (isValid(board, x + dir[0], y + dir[1])) {
                    x += dir[0];
                    y += dir[1];
                    System.out.println("This x = " + x + " y = " + y);
                    if (curVal + 1 < board[x][y] && !visited[x][y]) {
                        board[x][y] = curVal + 1;
                        System.out.println("board = " + board[x][y]);
                        queue.offer(new int[]{x, y});
                        visited[x][y] = true;
                    }
                }
            }
        }
    }

    public static boolean isValid(int[][] board, int row, int col) {
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length || board[row][col] == -1) return false;
        return true;
    }

}