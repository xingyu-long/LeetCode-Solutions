package com.leetcode.dynamic_programming;

public class _63_UniquePathsII {


    /**
     * 63. Unique Paths II
     * When: 2019/5/7
     * Review1:2019/6/19
     * Review2:2019/7/30
     * review3:2019/10/4
     * Difficulty: Medium
     * time: O(m * n)
     * space: O(n)
     *
     * @param obstacleGrid
     * @return
     */
    // 遇到1的时候当前的结果就是0，就是不可行。这里直接可以跟原来的方法相加，是因为本身res数组就是0，如果当前行前面出现了wall那么结果就会为0，后面计算也依然为0.
    // 这种思路要好好思考
    // time:O(m * n) space:O(n)
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int length = obstacleGrid[0].length;
        int[] res = new int[length];
        res[0] = 1;
        for (int i = 0; i < obstacleGrid.length; i++) {
            for (int j = 0; j < length; j++) {
                if (obstacleGrid[i][j] == 1) {
                    res[j] = 0;
                } else if (j > 0) { // 注意这里的 > 0 以及上面是从0开始。
                    // 因为第0个位置如果没有wall就是1
                    res[j] += res[j - 1];
                }
            }
        }
        return res[length - 1];
    }

    //time:O(m * n) space:O(1)
    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        // 就使用原来的这个数组作为返回
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1) {
            return 0;
        }

        // 将走过“能走”过的位置设置为1 否则为0; 并且要检查左边或者上边是否能够通过
        obstacleGrid[0][0] = 1;

        //初始化第一行（由于从一开始初始化为1，所以验证的就是当前一定要为0，以及前面那个为1 就可以通过 否则不可以）
        for (int i = 1; i < n; i++) {
            obstacleGrid[0][i] = (obstacleGrid[0][i] == 0 && obstacleGrid[0][i - 1] == 1) ? 1 : 0;
        }

        //初始化第一列
        for (int i = 1; i < m; i++) {
            obstacleGrid[i][0] = (obstacleGrid[i][0] == 0 && obstacleGrid[i - 1][0] == 1) ? 1 : 0;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0) {
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1] + obstacleGrid[i - 1][j];
                } else {
                    obstacleGrid[i][j] = 0;
                }
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }


    public static void main(String[] args) {
        int[][] test = new int[1][1];
        test[0][0] = 1;
        System.out.println(uniquePathsWithObstacles(test));
    }
}
