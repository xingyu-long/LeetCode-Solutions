package com.leetcode.dynamicProgramming;

public class _70_ClimbingStairs {

    /**
     * 70. Climbing Stairs
     * When: 2019/05/03
     * <p>
     * solution:
     * 分解问题：
     * 如果n = 1的话 就只能跳一步 然后选择=1
     * 如果n = 2的话 可以跳两步也可以两个一步 所以选择是2
     * 如果n = i的话 反着看来 可以从i-1 这里跳一步 或者是 i-2跳两步
     * f(1) = 1;
     * f(2) = 2;
     * f(3) = f(1) + f(2)
     *
     * @param n
     * @return
     */
    // 递归的形式  time: O(2^n) (类似于tree) space: O(n)
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        } else {
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }


    /**
     * test case:
     * n = 5
     * oneStep = 1, twoStep = 1, res = 0;
     *
     * i = 2
     * res = 1 + 1 = 2, twoStep = 1, oneStep = 2;
     * i = 3
     * res = 2 + 1 = 3, twoStep = 2, oneStep = 3;
     * i = 4
     * res = 2 + 3 = 5, twoStep = 3, oneStep = 5;
     * i = 5
     * res = 3 + 5 = 8
     * @param n
     * @return
     */
    // 迭代的形式 time: O(n) space: O(1)
    public int climbStairs2(int n) {
        if (n <= 1) return 1;
        int oneStep = 1, twoStep = 1, res = 0;// 这里的oneStep和twoStep 都是代表走该种情况下的“多少种可能”并非实际分别走了多少
        for (int i = 2; i <= n; i++) {
            res = oneStep + twoStep;
            twoStep = oneStep;
            oneStep = res;
        }
        return res;
    }
}