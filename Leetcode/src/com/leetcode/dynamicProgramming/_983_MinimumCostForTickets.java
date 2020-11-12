package com.leetcode.dynamicProgramming;

import java.util.HashSet;

/**
 * @Date: 05/06/2020, 07/11/2020
 * @Description: DP, 往前看
 **/
public class _983_MinimumCostForTickets {

    // https://leetcode.com/problems/minimum-cost-for-tickets/discuss/226659/Two-DP-solutions-with-pictures
    // 相当于从后往前看，然后dp[i]然后到达i这个位置最少话的钱的数目。 这里应该可以用二维coin change的思路。
    // time:O(n) space:O(max ~ 365)
    // 这个和那个painting的题 异曲同工之妙，需要注意并不是第七天才能买7天票， 所以是`Math.min(dp[Math.max(0, i - 7)] + costs[1]`
    public int mincostTickets(int[] days, int[] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        int num = days.length;
        int lastDay = days[num - 1];
        int[] dp = new int[lastDay + 1];
        HashSet<Integer> set = new HashSet<>();
        for (int day : days) {
            set.add(day);
        }
        for (int i = 1; i <= lastDay; i++) {
            if (!set.contains(i)) {
                dp[i] = dp[i - 1]; // 保存到目前为止的花的最少的钱。
            } else {
                dp[i] = Math.min(dp[i - 1] + costs[0], Math.min(dp[Math.max(0, i - 7)] + costs[1],
                    dp[Math.max(0, i - 30)] + costs[2]));
            }
        }
        return dp[lastDay];
    }
}
