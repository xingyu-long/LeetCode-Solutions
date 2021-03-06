package com.leetcode.array;

/**
 * @Date: 05/10/2020
 * @Description: Directed Graph,
 **/
public class _997_FindtheTownJudge {
    // 和227一样，只是没有那个know函数，我们也可以做的更有效一些
    // Judge的入度应该是N - 1，检查当前点的degree
    public int findJudge(int N, int[][] trust) {
        int[] count = new int[N + 1];
        for (int[] edge : trust) {
            count[edge[0]]--;
            count[edge[1]]++;
        }

        for (int i = 1; i <= N; i++) {
            if (count[i] == N - 1) return i;
        }

        return -1;
    }
}
