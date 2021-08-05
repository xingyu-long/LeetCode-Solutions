/*
 * @Date: 03/26/2020 11:06:42
 * @LastEditTime: 07/29/2021 19:18:22
 * @Description: Search
 */

package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

public class _542_01Matrix {
    // time:O(m * n * find(math.max(m, n)))
    // TLE
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return new int[][] {};
        int m = matrix.length;
        int n = matrix[0].length;
        // 利用BFS吧 最短路问题
        int[][] res = new int[m][n];
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] != 0) {
                    res[i][j] = bfs(matrix, i, j, dirs);
                }
            }
        }
        return res;
    }


    public int bfs(int[][] matrix, int i, int j, int[][] dirs) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[] {i, j});
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                if (matrix[curr[0]][curr[1]] == 0)
                    return level;
                for (int[] dir : dirs) {
                    int x = curr[0] + dir[0];
                    int y = curr[1] + dir[1];
                    if (x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length)
                        continue;
                    queue.offer(new int[] {x, y});
                }
            }
            level++;
        }
        return -1;
    }
    
    // 每个元素进去一次。
    // Time: O(m * n)
    public int[][] updateMatrix2(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) return new int[][]{};
        int m = matrix.length;
        int n = matrix[0].length;
        // 利用BFS吧 最短路问题
        Queue<int[]> queue = new LinkedList<>();
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            for (int[] dir : dirs) {
                int x = curr[0] + dir[0];
                int y = curr[1] + dir[1];
                if (x < 0 || x >= matrix.length || 
                    y < 0 || y >= matrix[0].length || 
                    matrix[x][y] <= 1 + matrix[curr[0]][curr[1]])
                    continue;
                queue.offer(new int[]{x, y});
                matrix[x][y] = matrix[curr[0]][curr[1]] + 1;
            }
        }
        return matrix;
    } 
}
