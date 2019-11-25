package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _174_DungeonGame {
    public static int minInit(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        int[][] dp = new int[m+1][n+1];
        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[m][n-1] = dp[m-1][n] = 1;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                dp[i][j] = Math.min(dp[i+1][j], dp[i][j+1]) - dungeon[i][j];
                if (dp[i][j] < 0) dp[i][j] = 1; // 表示只init HP为1就可以（因为这个PK是加血的那种）
                System.out.print("dp[" + i+"]["+j+"] = " + dp[i][j]+" ");
            }
            System.out.println();
        }
        return dp[0][0];
    }
    public static void main(String[] args) {
        int[][] dungeon = {{-2, -3, 3}, {-5, -10, 1}, {10, 30, -5}};
        System.out.println(minInit(dungeon));
    }
}
