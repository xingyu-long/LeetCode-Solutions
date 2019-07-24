package com.leetcode.bfsANDdfs;

import java.util.LinkedList;
import java.util.Queue;

public class _200_NumberofIslands {

    /**
     *  200. Number of Islands
     *  When: 2019/05/31
     *  Review1: 2019/7/24
     *
     * solution:
     * 使用dfs，四个方向进行搜索。
     *
     * ？
     * 这个和backtracking有什么区别呢？？ 这个是没有回溯的过程！！！！
     * @param grid
     * @return
     */
    // time: O(m * n) space: O(n)
    public int numIslands(char[][] grid) {
        int res = 0;
        if (grid == null ||  grid.length == 0) return res;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length
                || j >= grid[0].length || grid[i][j] == '0')
            return; //这里是i >= 并且j >= 因为0开始，到length的时候就out of index了
        grid[i][j] = '0';//表示该点已经被搜索过了
        //上下左右
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
    }

    //利用bfs实现
    //time: O(m * n) space:O(n)
    public int numIslands2(char[][] grid) {
        int res = 0;
        if (grid == null ||  grid.length == 0) return res;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    //bfs就不会递归调用了
    public void bfs(char[][] grid, int x, int y) {
        Queue<Integer> queue = new LinkedList<>();
        // 需要把坐标值放入queue里面去，可以使用 当前行*每行个数 + 现在的j就是位置
        grid[x][y] = '0';
        int n = grid.length;
        int m = grid[0].length;
        int pos = x * m + y;
        queue.offer(pos);
        while (!queue.isEmpty()) {
            pos = queue.poll();
            int i = pos / m; //求所在行
            int j = pos % m; //求所在列
            //四个方向
            if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                queue.offer((i - 1) * m + j);
                grid[i - 1][j] = '0';
            }
            if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                queue.offer(i * m + (j - 1));
                grid[i][j - 1] = '0';
            }
            if (i + 1 < n && grid[i + 1][j] == '1') {
                queue.offer((i + 1) * m + j);
                grid[i + 1][j] = '0';
            }
            if (j + 1 < m && grid[i][j + 1] == '1') {
                queue.offer(i * m + (j + 1));
                grid[i][j + 1] = '0';
            }
        }
    }
}
