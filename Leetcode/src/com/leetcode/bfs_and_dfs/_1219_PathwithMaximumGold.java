package com.leetcode.bfs_and_dfs;

public class _1219_PathwithMaximumGold {
    int res = 0;
    public int getMaximumGold(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0)
                    dfs(grid, i, j, new boolean[m][n], 0);
            }
        }
        return res;
    }

    // 不要写错了限制条件！！！
    public void dfs(int[][] grid, int row, int col, boolean[][] visited, int sum) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length ||
                visited[row][col] || grid[row][col] == 0) {
            res = Math.max(res, sum);
            return;
        }
        visited[row][col] = true;
        int val = grid[row][col];
        dfs(grid, row, col + 1, visited, sum + val);
        dfs(grid, row, col - 1, visited, sum + val);
        dfs(grid, row + 1, col, visited, sum + val);
        dfs(grid, row - 1, col, visited, sum + val);
        visited[row][col] = false;
    }

    // backtracking的写法
    public int getMaximumGold2(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] != 0) {
                    int max = dfs(grid, i, j, visited);
                    res = Math.max(res, max);
                }
            }
        }
        return res;
    }

    public int dfs(int[][] grid, int row, int col, boolean[][] visited) {
        // 那个0导致没有返回！！！！
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col] || grid[row][col] == 0) {
            return 0;
        }
        visited[row][col] = true;
        int val = grid[row][col];
        int right = dfs(grid, row, col + 1, visited);
        int left = dfs(grid, row, col - 1, visited);
        int down = dfs(grid, row + 1, col, visited);
        int up = dfs(grid, row - 1, col, visited);
        int max = val + Math.max(right, Math.max(left, Math.max(down, up)));
        visited[row][col] = false;
        return max;
    }


    public static void main(String[] args) {
        _1219_PathwithMaximumGold path = new _1219_PathwithMaximumGold();
        int[][] grid = {{0,6,0}, {5,8,7}, {0,9,0}};
        System.out.println(path.getMaximumGold(grid));
    }
}
