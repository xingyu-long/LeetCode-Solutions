package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

public class _490_TheMaze {
    /**
     * 490. The Maze
     * When:2019/10/4
     * review1:10/29/2019
     * Difficulty: Medium
     * 利用BFS，主要是注意这个球的状态，不是每次走一步，而是不停。
     *
     * @return
     */
    // BFS
    public static boolean hasPath2(int[][] board, int[] start, int[] end) {
        if (board == null || board.length == 0 ||
                board[0] == null || board[0].length == 0) {
            return false;
        }
        Queue<int[]> queue = new LinkedList<>();
        // define the directions 上,左,右,下
        int[][] directions = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
//        int [][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        // 标记数组？
        boolean[][] visited = new boolean[board.length][board[0].length];
        queue.offer(start);
        while (!queue.isEmpty()) {
            int[] curPoint = queue.poll();
            visited[curPoint[0]][curPoint[1]] = true;
            if (curPoint[0] == end[0] && curPoint[1] == end[1]) return true;
            for (int[] direction : directions) {
                // 这里需要加一个每次的当前点！！！
                int row = curPoint[0];
                int col = curPoint[1];
                while (isValid(board, row + direction[0], col + direction[1])) {
                    row += direction[0];
                    col += direction[1];
                }
                if (!visited[row][col]) {
                    queue.offer(new int[]{row, col});
                }
            }
        }
        return false;
    }
    public static boolean hasPath(int[][] board, int[] start, int[] end) {
        if (board == null || board.length == 0 ||
                board[0] == null || board[0].length == 0) {
            return false;
        }
        boolean[][] visited = new boolean[board.length][board[0].length];
        // visited[start[0]][start[1]] = true;
        return dfs(board, start[0], start[1], end, visited);
    }

    public static boolean dfs(int[][] board, int row, int col, int[] end, boolean[][] visited) {
        // 记住需要检查visited！！！！！！
        if (!isValid(board, row, col) || visited[row][col]) return false;
        visited[row][col] = true;
        if (row == end[0] && col == end[1]) return true;
        int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        for (int[] direction : directions) {
            int x = row;
            int y = col;
            while (isValid(board, x + direction[0], y + direction[1])) {
                x += direction[0];
                y += direction[1];
            }
            if (dfs(board, x, y, end, visited)) return true;
        }
        return false;
    }

    public static boolean isValid(int[][] board, int row, int col) {
        return (row >= 0 && row < board.length && col >= 0 && col < board[0].length && board[row][col] == 0);
    }


    public static void main(String[] args) {
        int[][] board = {{0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {1, 1, 0, 1, 1},
                {0, 0, 0, 0, 0}};
        int[] start = {0, 4};
        int[] end = {4, 4};
        System.out.println(hasPath(board, start, end));
//        System.out.println(hasPath2(board, start, end));
    }


}
