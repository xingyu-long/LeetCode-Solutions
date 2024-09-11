/*
 * @Date: 2020-04-01 16:01:18
 * 
 * @LastEditors: Clark long
 * 
 * @LastEditTime: 2020-04-01 16:23:13
 */
package com.leetcode.dynamic_programming;

import java.util.Arrays;

public class _1335_MinimumDifficultyofaJobSchedule {
    // 分组DP
    public int minDifficulty(int[] jobDifficulty, int d) {
        // finish j jobs and then i job.
        // i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i
        // [6,5,4,3, |2 |1] 分组 DP
        if (jobDifficulty == null || jobDifficulty.length < d)
            return -1;
        int n = jobDifficulty.length;
        int[][] memo = new int[n + 1][d + 1];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return dfs(jobDifficulty, d, jobDifficulty.length - 1, memo);
    }

    public int dfs(int[] A, int d, int j, int[][] memo) {
        // base case 其实还有j + 1 == d的时候，直接返回和，但这里的复杂度一样所以就没这么写了
        if (d == 1) {
            int max = 0;
            while (j >= 0) {
                max = Math.max(max, A[j]);
                j--;
            }
            return max;
        }
        if (memo[j][d] != -1)
            return memo[j][d];
        int res = Integer.MAX_VALUE / 2, currMax = A[j];
        for (int i = j - 1; i >= 0; i--) {
            // 每次计算currMax,这样可以优化计算
            res = Math.min(res, dfs(A, d - 1, i, memo) + currMax);
            currMax = Math.max(currMax, A[i]);
        }
        memo[j][d] = res;
        return res;
    }

    public static void main(String[] args) {
        int[] A = {6, 5, 4, 3, 2, 1};
        int d = 2;
        _1335_MinimumDifficultyofaJobSchedule schedule =
                new _1335_MinimumDifficultyofaJobSchedule();
        System.out.println(schedule.minDifficulty(A, d));
    }
}
