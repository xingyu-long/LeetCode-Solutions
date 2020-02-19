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
    // union find的解法，先把存在的所有'1'标记为单个的connected component然后使用i * row + j来表示坐标。
    // 几个方向走，如果成立就union 最后返回count。
    // https://leetcode.com/problems/number-of-islands/discuss/56354/1D-Union-Find-Java-solution-easily-generalized-to-other-problems
    // time:O(m * n) space:O(m * n) 也可以改变原有的数组这样就是O(1)的space。
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) return 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1' && !visited[i][j]) {
                    helper(grid, i, j, visited);
                    res++;// 并不还原状态 不是backtracking。
                }
            }
        }
        return res;
    }

    public void helper(char[][] grid, int row, int col, boolean[][] visited) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) return;
        if (grid[row][col] != '1') return;
        if (visited[row][col]) return;
        visited[row][col] = true;
        int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        for (int[] dir : dirs) {
            helper(grid, row + dir[0], col + dir[1], visited);
        }
    }

    // 为啥会TLE? 后面在bfs中，加入这个点之后就表示访问了！
    public static void bfs(char[][] grid, int row, int col) {
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

    public class UF {
        int count;
        int[] id;
        int[] size;
        public UF(char[][] grid) {
            int m = grid.length;
            int n = grid[0].length;
            id = new int[m * n];
            size = new int[m * n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == '1') { // 这里需要注意
                        count++;
                        int idx = i * n + j;
                        id[idx] = idx;
                        size[idx] = 1;
                    }
                }
            }
        }

        public int find(int p) {
            while (id[p] != p) {
                p = id[p];
            }
            return p;
        }

        public void union(int p, int q) {
            int pid = find(p);
            int qid = find(q);
            if (pid == qid) return;
            if (size[pid] > size[qid]) {
                size[pid] += size[qid];
                id[qid] = pid;
            } else {
                size[qid] += size[pid];
                id[pid] = qid;
            }
            count--;
        }
    }

    // union find.
    public int numIslands3(char[][] grid) {
        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) return 0;
        UF uf = new UF(grid);
        int m = grid.length;
        int n = grid[0].length;
        int[][] dirs = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
        // boolean[][] visited = new boolean[m][n];
        // 不能用visited数组，否则无法union。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 这里如何走 让其可以union？
                if (grid[i][j] == '1') {
                    for (int[] dir : dirs) {
                        int x2 = i + dir[0];
                        int y2 = j + dir[1];
                        if (x2 >= 0 && x2 < m && y2 >= 0 && y2 < n && grid[x2][y2] == '1')
                            uf.union(i * n + j, x2 * n + y2);
                    }
                }
            }
        }
        return uf.count;
    }
}
