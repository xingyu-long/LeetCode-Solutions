package com.leetcode.dynamicProgramming;

public class _62_UniquePaths {

    /**
     *
     * When: 262. Unique Paths019/05/07
     * Review1: 2019/6/19
     * Review2:2019/7/30
     * review3:2019/10/4
     * <p>
     * solution:
     * 主要还是动态规划，并且使用从下到上
     * 当前位置的路数 等于 左边的值+上面的值
     *
     * @param m
     * @param n
     * @return
     */
    // time: O(n * m) space: (n * m)
    public int uniquePaths(int m, int n) {
        int[][] res = new int[m][n];
        for (int i = 0; i < m; i++) {
            res[i][0] = 1;
        }

        for (int i = 0; i < n; i++) {
            res[0][i] = 1;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[i][j] = res[i - 1][j] + res[i][j - 1];
            }
        }
        return res[m - 1][n - 1];
    }

    // time: O(n*m) space: O(n)
    // 这个相当于降低 空间复杂度 然后计算每层的值。这里数组初始化的时候 都为空
    public int uniquePaths2(int m, int n) {
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 0; i < m; i++) {
            for (int j = 1; j < n; j++) {
                res[j] = res[j] + res[j - 1];
            }
        }
        return res[n - 1];
    }

    //这里还有种方法 现在不太能理解（利用排列组合，后面再看）
    // https://www.youtube.com/watch?v=M8BYckxI8_U
    public int uniquePaths3(int m, int n) {
        int count = m + n - 2;
        int k = m - 1;
        double res = 1;
        for (int i = 1; i <= k; i++) {
            res = res * (count - k + i) / i;
        }
        return (int) res;
    }
}
