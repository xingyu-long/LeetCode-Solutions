package com.leetcode.backtracking;

/**
 * @Date: 07/13/2020
 * @Description: backtracking
 **/
public class _351_AndroidUnlockPatterns {

    public int numberOfPatterns(int m, int n) {
        // 表示从x到y一定要经过哪个数。
        int[][] skip = new int[10][10];
        skip[1][3] = skip[3][1] = 2;
        skip[1][7] = skip[7][1] = 4;
        skip[3][9] = skip[9][3] = 6;
        skip[7][9] = skip[9][7] = 8;
        skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;
        boolean[] visited = new boolean[10];
        int res = 0; 
        // 接下来因为1,3,7,9对称。2,4,6,9对称。以及5
        for (int i = m; i <= n; i++) {
            res += dfs(visited, skip, 1, i - 1) * 4;
            res += dfs(visited, skip, 2, i - 1) * 4;
            res += dfs(visited, skip, 5, i - 1);
        }
        return res;
    }

    public int dfs(boolean[] visited, int[][] skip, int start, int remain) {
        if (remain < 0) return 0;
        if (remain == 0) return 1;
        visited[start] = true;
        int res = 0;
        // 从start出发去九个点。
        for (int i = 1; i <= 9; i++) {
            // 目标位置应该是未访问的，但如果存在中间的点，则需要访问过才行！
            if (!visited[i] && (skip[start][i] == 0 || visited[skip[start][i]])) {
                res += dfs(visited, skip, i, remain - 1);
            }
        }
        visited[start] = false;
        return res;
    }
}