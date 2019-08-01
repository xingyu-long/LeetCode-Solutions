package com.leetcode.dynamicProgramming;

public class _256_PaintHouse {
    /**
     *  256. Paint House
     *  When:2019/8/1
     *  Difficulty: Easy

         There are a row of n houses, each house can be painted with one of the three colors:
         red, blue or green. The cost of painting each house with a certain color is different.
         You have to paint all the houses such that no two adjacent houses have the same color.

         The cost of painting each house with a certain color is represented by a n x 3 cost matrix. For example,
         costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with
         color green, and so on... Find the minimum cost to paint all houses.

        dp[i][j] 表示第i的房子用j的最小花费
        由于不可以连续两个相同的颜色，所以只需要加下上一个其他两个颜色便宜的情况
        solution: https://www.cnblogs.com/grandyang/p/5319384.html
     */
    // time:O(n) space:O(1)
    public int minCost(int[][] costs) {
        if (costs == null || costs[0].length == 0) return 0;
        for (int i = 1; i < costs.length; i++) {
            //前两个的最小
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);
            costs[i][1] += Math.min(costs[i - 1][2], costs[i - 1][0]);
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        int n = costs.length - 1;
        return Math.min(costs[n][0], Math.min(costs[n][1], costs[n][2])); //比较最后的方案
    }
}
