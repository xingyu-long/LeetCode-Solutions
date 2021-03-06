/*
 * @Date: 12/21/2019 20:54:57
 * @LastEditTime: 12/10/2020 10:33:22
 * @Description: DP
 */
package com.leetcode.math;

import java.util.Arrays;
import java.util.HashMap;

public class _397_IntegerReplacement {

    // 内部用step。 没办法使用memo。
    public int integerReplacement2(int n) {
        return helper(n, 0);
    }

    public int helper(long n, int step) {
        if (n == 1)
            return step;
        if (n % 2 == 0) {
            return helper(n / 2, step + 1);
        } else {
            return Math.min(helper(n + 1, step + 1), helper(n - 1, step + 1));
        }
    }

    // 利用long防止溢出。
    public int integerReplacement3(int n) {
        HashMap<Long, Integer> map = new HashMap<>();
        return helper(n, map);
    }

    // 这里计算是多少步，并非有多少种走法，所以不能定义在base case那里。
    // 利用 count = 1 + xxx; 这样比较好理解。
    public int helper(long n, HashMap<Long, Integer> map) {
        if (map.get(n) != null)
            return map.get(n);
        if (n == 1)
            return 0;
        int count = 0;
        if (n % 2 == 0) {
            count = 1 + helper(n / 2, map);
        } else {
            count = 1 + Math.min(helper(n + 1, map), helper(n - 1, map));
        }
        map.put(n, count);
        return count;
    }

    // 这个如果是写bottom-up, 不太行，因为奇偶数限制的，而另外的例题不一样。
    // 类似题目：（1）如果能被2整除，可以选择这个 （2）如果能被3整除，可以选择这个（3）也可以只减1.
    // 最大的不同是，397这个题是通过奇偶性来判断用啥的，而这个题是满足条件都可以采取。
    public int waysByBottomUp(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i = 1; i < n; i++) {
            dp[i + 1] = Math.min(dp[i + 1], dp[i] + 1);
            if (i * 2 <= n)
                dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
            if (i * 3 <= n)
                dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
        }
        return dp[n];
    }
}