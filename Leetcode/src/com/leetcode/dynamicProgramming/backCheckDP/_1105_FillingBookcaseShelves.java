package com.leetcode.dynamicProgramming.backCheckDP;

/**
 * @Date: 04/14/2020
 * @Description: DP, 往回看
 **/
public class _1105_FillingBookcaseShelves {

    // time:O(n^2) space:O(n)
    // 题目的意思是顺序不能变，只能放置不同层
    public int minHeightShelves(int[][] books, int shelf_width) {
        int n = books.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            int w = books[i - 1][0];
            int h = books[i - 1][1];
            dp[i] = dp[i - 1] + h;
            int sum = w;
            for (int j = i - 1; j > 0; j--) { // 看以j-1这个的book为止能否放到当前层。
                sum += books[j - 1][0];
                if (sum > shelf_width) {
                    break;
                } else {
                    h = Math.max(h, books[j - 1][1]);
                    dp[i] = Math.min(dp[i], dp[j - 1] + h);
                    // 将上一层的最后放到自己这里来。所以总的就是上一层的倒数第二个元素（这里的j-1）+当前
                }
            }
        }
        return dp[n];
    }
}
