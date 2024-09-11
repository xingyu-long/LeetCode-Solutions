package com.leetcode.dynamic_programming;

import java.util.Arrays;

/**
 * @Date: 08/29/2020
 * @Description: DP
 **/
public class _343_IntegerBreak {
    // 看分解的情况，一种是直接分为两个，一种是将i-j继续分解为多个的情况
    // https://leetcode.com/problems/integer-break/discuss/80694/Java-DP-solutio 里面的第一条评论
    public int integerBreak(int n) {
        if (n < 1) return 0;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i/2; j++) { // 因为后一半都是计算相同的情况
                // 因为一个数可能被分为多个，所以要以来以前的最优结果。
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }
        return dp[n];
    }

    // DFS + memo
    // time:O(n^2) space:O(n)
    public int integerBreak2(int n) {
        int[] memo = new int[n + 1];
        Arrays.fill(memo, -1);
        memo[1] = 1;
        return dfs(n, memo);
    }

    public int dfs(int n, int[] memo) {
        if (memo[n] != -1) return memo[n];
        if (n == 1) return 1;
        int res = 0;
        for (int i = 1; i <= n / 2; i++) {
            res = Math.max(res, Math.max(i, dfs(i, memo)) * Math.max(n - i, dfs(n - i, memo)));
        }
        memo[n] = res;
        return res;
    }

    // time:O(n) 数学规律
    public int integerBreak3(int n) {
        /*
        数字4拆成 2+2，乘积最大，为4。

        数字5拆成 3+2，乘积最大，为6。

        数字6拆成 3+3，乘积最大，为9。

        数字7拆为 3+4，乘积最大，为 12。

        数字8拆为 3+3+2，乘积最大，为 18。

        数字9拆为 3+3+3，乘积最大，为 27。

        数字10拆为 3+3+4，乘积最大，为 36。
        */

        if (n == 2 || n == 3) return n - 1;
        int res = 1;
        while (n > 4) {
            res *= 3;
            n -= 3;
        }
        return res * n;
    }
}