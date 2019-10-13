package com.leetcode.dynamicProgramming;

import java.util.List;

public class _120_Triangle {

    /**
     * 120. Triangle
     * When: 2019/5/7
     * Review1: 2019/7/30
     * review2: 2019/10/4
     * <p>
     * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers
     * on the row below.
     * <p>
     * For example, given the following triangle
     * [
     * [2],
     * [3,4],
     * [6,5,7],
     * [4,1,8,3]
     * ]
     * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
     * <p>
     * 第i层 : j
     * 第i + 1层 : j, j + 1
     * <p>
     * test case:
     * 倒着来
     * res = [0, 0, 0, 0, 0]
     * i = 3: [4, 1, 8, 3, 0]
     * [6, 5, 7]
     * i = 2: [7, 6, 10, 3, 0]
     * [3, 4]
     * i = 1: [9, 10, 10, 3, 0]
     * [2]
     * i = 0: [11, 10, 10, 3, 0]
     * return res[0] = 11
     *
     * @param triangle
     * @return
     */
    /**
     * [2]
     * [3,4]
     * [6,5,7]
     * [4,1,8,3]
     * DP问题
     * 状态转移方程
     * dp[i][j] = dp[i][j] + min{dp[i-1][j], dp[i-1][j-1]} j > 0 && j < n;
     * dp[i][j] = dp[i][j] + dp[i - 1][j] j = 0; // 只能选择上面的数相加
     * dp[i][j] = dp[i][j] + dp[i - 1][j - 1] j = n;
     * <p>
     * 初始状态
     * 原数据
     **/
    // 不能使用贪心的思想 eg: [1][2, 3][3, 10, -5] 贪心的结果为5，但是全局最小是-1
    // 这里只能用xxx.set因为得到的结果是value而不是变量。所以不能+=
    // time: O(m * n) space:O(1)
    public int minimumTotal(List<List<Integer>> triangle) {
        int m = triangle.size();
        if (triangle == null || triangle.size() == 0) return 0;
        for (int i = 1; i < triangle.size(); i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                if (j == 0) {
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i - 1).get(j));
                } else if (j == triangle.get(i).size() - 1) {
                    triangle.get(i).set(j, triangle.get(i).get(j) + triangle.get(i - 1).get(j - 1));
                } else {
                    triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i - 1).get(j - 1), triangle.get(i - 1).get(j)));
                }
            }
        }
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.get(m - 1).size(); i++) {
            res = Math.min(res, triangle.get(m - 1).get(i));
        }
        return res;
    }

    // 从下往上，最后的结果直接获取
    // time: (n^2) space: O(n)
    public int minimumTotal2(List<List<Integer>> triangle) {
        int[] res = new int[triangle.size() + 1]; //这里是因为后面有个j+1 防止溢出
        for (int i = triangle.size() - 1; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                // j = 0这时候相当于赋值，然后为原有数据
                res[j] = Math.min(res[j], res[j + 1]) + triangle.get(i).get(j);
            }
        }
        return res[0];
    }

}