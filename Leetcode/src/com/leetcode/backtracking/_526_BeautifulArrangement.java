/*
 * @Date: 10/10/2020 20:19:10
 * @LastEditTime: 01/03/2021 09:57:04
 * @Description: backtracking
 */
package com.leetcode.backtracking;

public class _526_BeautifulArrangement {
    // Time:(2^N)
    public int countArrangement(int N) {
        // backtracking?
        boolean[] visited = new boolean[N + 1];
        return dfs(N, visited, 0);
    }

    // count表示位置，i=[1,N]表示需要放置的每个值
    public int dfs(int N, boolean[] visited, int count) {
        if (count == N) {
            return 1;
        }

        int res = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i])
                continue;
            if (i % (count + 1) == 0 || (count + 1) % i == 0) {
                visited[i] = true;
                res += dfs(N, visited, count + 1);
                visited[i] = false;
            }
        }
        return res;
    }
}
