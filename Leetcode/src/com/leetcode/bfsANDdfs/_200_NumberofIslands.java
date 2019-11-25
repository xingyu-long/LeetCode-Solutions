package com.leetcode.bfsANDdfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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
    public static int numIslands(char[][] grid) {
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

    public static void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length
                || j >= grid[0].length || grid[i][j] != '1')
            return; //这里是i >= 并且j >= 因为0开始，到length的时候就out of index了
        grid[i][j] = '0';//表示该点已经被搜索过了
        //上下左右
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
    }

    // 上面这种方法巧妙的利用了'0'不能访问的情况！
    boolean[][] visited; // 利用记忆数组的dfs
    public int numIslands4(char[][] grid) {
        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) return 0;
        int res = 0;
        visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                // 这里需要加这个，表示为1并且没有访问过才会访问！
                if (!visited[i][j] && grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    public void dfs2(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || visited[row][col] || grid[row][col] == '0') return; // 这里不要忘了'0'是不可以访问的。
        visited[row][col] = true;
        dfs2(grid, row, col - 1);
        dfs2(grid, row, col + 1);
        dfs2(grid, row - 1, col);
        dfs2(grid, row + 1, col);
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

    public static int numIslands3(char[][] grid) {
        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) return 0;
        int res = 0;
        // how to deal it with BFS method?  add 1 after entering while loop
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    bfs2(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    // 为啥会TLE? 后面在bfs中，加入这个点之后就表示访问了！
    public static void bfs2(char[][] grid, int row, int col) {
        // 又是这里的问题！！！
        if (!isValid(grid, row, col)) return;
        Queue<int[]> queue = new LinkedList<>();
        grid[row][col] = '0'; // 表示已访问
        queue.offer(new int[]{row, col});
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] dir : dirs) {
                int x = cur[0];
                int y = cur[1];
                if (isValid(grid, x + dir[0], y + dir[1])) {
                    queue.offer(new int[]{x + dir[0], y + dir[1]});
                    grid[x + dir[0]][y + dir[1]] = '0';
                }
            }
        }
    }

    public static boolean isValid(char[][] grid, int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 ||
                col >= grid[0].length || grid[row][col] != '1') return false;
        return true;
    }

    public static void main(String[] args) {
        char[][] grid = {{'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}};
//        System.out.println(numIslands(grid));

        Set<int[]> set = new HashSet<>();
        int[] p1 = new int[]{1,1};
        int[] p2 = new int[]{1,1};
        set.add(p1);
        set.add(p2);
        System.out.println(set.size());
    }

    public static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
