package com.leetcode.bfs_and_dfs;

import java.util.HashSet;

public class findPath {

    public boolean canReach(int[][] board, int[] start, int[] end) {
        if (board == null || board.length == 0 ||
                board[0] == null || board[0].length == 0) return false;

        return dfs(start[0],start[1], end, board, new HashSet<>());
    }

    public boolean dfs(int row, int col, int[] end, int[][] board, HashSet<Integer> visited) {
        if (row == end[0] && col == end[1]) return true;
        if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) return false;
        if (board[row][col] == 1) return false;
        int pos = row * board[0].length + col;
        if (visited.contains(pos)) return false;
        visited.add(pos);
        boolean up = dfs(row - 1, col, end, board, visited);
        boolean down = dfs(row + 1, col, end, board, visited);
        boolean left = dfs(row, col - 1, end, board, visited);
        boolean right = dfs(row, col + 1, end, board, visited);
        visited.remove(visited.size() - 1);
        return up || down || left || right;
    }

    public static void main(String[] args) {
        int[][] board = {{0,0,0}, {1,1,0}, {0,1,0}};
        findPath find = new findPath();
        int[] start = {0, 0};
        int[] end = {board.length - 1, board[0].length - 1};
        System.out.println(find.canReach(board, start, end));
    }
}
