package com.leetcode.bfsANDdfs;

import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 07/22/2020, 10/24/2020
 * @Description: DFS, Encode.
 **/
public class _694_NumberofDistinctIslands {

    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 ||
            grid[0] == null || grid[0].length == 0) {
            return 0;
        }
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Set<String> res = new HashSet<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j] && grid[i][j] == '1') {
                    Set<String> set = new HashSet<>();
                    helper(grid, i, j, i, j, visited, set);
                    res.add(set.toString());
                }
            }
        }
        return res.size();
    }

    public static void helper(char[][] grid, int row, int col, int baseX, int baseY,
        boolean[][] visited, Set<String> set) {
        if (row < 0 || row >= grid.length ||
            col < 0 || col >= grid[0].length) {
            return;
        }
        if (grid[row][col] != '1') {
            return;
        }
        if (visited[row][col]) {
            return;
        }
        visited[row][col] = true;
        set.add((row - baseX) + "_" + (col - baseY));
        helper(grid, row + 1, col, baseX, baseY, visited, set);
        helper(grid, row - 1, col, baseX, baseY, visited, set);
        helper(grid, row, col + 1, baseX, baseY, visited, set);
        helper(grid, row, col - 1, baseX, baseY, visited, set);
    }

    public static void main(String[] args) {
        char[][] grid = {{'1', '1', '0', '0', '0'},
            {'0', '0', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}};
        numIslands(grid);
    }
}
