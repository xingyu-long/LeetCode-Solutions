/*
 * @Date: 03/26/2020 11:06:42
 * @LastEditTime: 07/29/2021 19:18:22
 * @Description: Search
 */

package com.leetcode.bfs_and_dfs;

import java.util.LinkedList;
import java.util.Queue;

public class _542_01Matrix {
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    // time: O(m * n) space: O(1)
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    mat[i][j] = -1; // mark it as unprocessed yet.
                }
            }
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int newX = curr[0] + dir[0];
                    int newY = curr[1] + dir[1];
                    if (isValid(mat, newX, newY)) {
                        queue.offer(new int[]{newX, newY});
                        mat[newX][newY] = mat[curr[0]][curr[1]] + 1;
                    }
                }
            }
        }
        return mat;
    }
    
    public boolean isValid(int[][] mat, int x, int y) {
        if (x < 0 || x >= mat.length || y < 0 || y >= mat[0].length) return false;
        if (mat[x][y] != -1) return false;
        return true;
    }
}
