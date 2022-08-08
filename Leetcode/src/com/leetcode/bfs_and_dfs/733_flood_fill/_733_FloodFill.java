package com.leetcode.bfs_and_dfs;

public class _733_FloodFill {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 ||
                image[0] == null || image[0].length == 0) return new int[][]{};
        int color = image[sr][sc];
        if (color == newColor) return image;
        helper(image, sr, sc, color, newColor);
        return image;
    }

    // DFS
    public void helper(int[][] image, int row, int col, int color, int newColor) {
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length) return;
        if (image[row][col] != color) return;
        image[row][col] = newColor;
        int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        for (int [] dir : dirs) {
            helper(image, row + dir[0], col + dir[1], color, newColor);
        }
    }
}
