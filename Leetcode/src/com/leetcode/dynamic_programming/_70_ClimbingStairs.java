package com.leetcode.dynamic_programming;

import java.util.HashMap;

public class _70_ClimbingStairs {

    /**
     * 70. Climbing Stairs
     * When: 2019/05/03
     * review2:2019/10/4
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
    // https://www.geeksforgeeks.org/count-ways-reach-nth-stair/ n个的情况。
    // 递归的形式  time: O(n) space: O(n) 因为有 memoization不然就是2^n(tree的节点数)
    public int climbStairs(int n) {
        HashMap<Integer, Integer> map = new HashMap<>();
        return helper(n, map);
    }

    public int helper(int n, HashMap<Integer, Integer> map) {
        if (map.containsKey(n)) return map.get(n);
        int val;
        if (n < 0) {
            val = 0;
        } else if (n == 0) {
            val = 1;
        } else {
            val = helper(n - 1, map) + helper(n - 2, map);
        }
        map.put(n, val);
        return val;
    }

    // 理解为 斐波那契数列
    // 从下到上开始计算
    // time: O(n) space: O(n)
    public int climbStairs2(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1; // 当只有0个梯子，所以只有一种情况，那就是保持不动
        dp[1] = 1; // 当只有1个梯子，所以有一种情况 就是跳一步
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; //表示要不就是从i-1这个位置跳一步 或者是 i-2这个位置跳两步的所有可能性的和
        }
        return dp[n];
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
    // 节省空间的解法
    // 迭代的形式 time: O(n) space: O(1)
    public int climbStairs3(int n) {
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