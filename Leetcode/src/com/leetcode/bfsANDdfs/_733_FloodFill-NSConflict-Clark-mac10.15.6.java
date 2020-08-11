package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 07/26/2020
 * @Description: DFS, BFS
 **/
public class _733_FloodFill {

    // 不用visited数组记录。
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image == null || image.length == 0 ||
            image[0] == null || image[0].length == 0) {
            return new int[][]{};
        }
        int color = image[sr][sc];
        if (color == newColor) {
            return image; // 如果相同的话，就不用搜索了。
        }
        dfs(image, sr, sc, color, newColor);
        return image;
    }

    public void dfs(int[][] image, int row, int col, int color, int newColor) {
        if (row < 0 || row >= image.length || col < 0 || col >= image[0].length) {
            return;
        }
        if (image[row][col] != color) {
            return;
        }
        image[row][col] = newColor;

        dfs(image, row + 1, col, color, newColor);
        dfs(image, row - 1, col, color, newColor);
        dfs(image, row, col + 1, color, newColor);
        dfs(image, row, col - 1, color, newColor);
    }

    private void bfs(int[][] image, int row, int col, int color, int newColor) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        image[row][col] = newColor;
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : dirs) {
                int x = curr[0] + dir[0], y = curr[1] + dir[1];
                if (x < 0 || x >= image.length || y < 0 || y >= image[0].length) {
                    continue;
                }
                if (image[x][y] != color) {
                    continue;
                }
                queue.offer(new int[]{x, y});
                image[x][y] = newColor;
            }
        }
    }
}
