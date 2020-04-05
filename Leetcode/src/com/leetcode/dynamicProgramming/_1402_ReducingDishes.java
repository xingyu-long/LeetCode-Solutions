package com.leetcode.dynamicProgramming;

import java.util.Arrays;

public class _1402_ReducingDishes {
    // time:O(nlogn) space:O(1)
    public int maxSatisfaction(int[] satisfaction) {
        if (satisfaction == null || satisfaction.length == 0) return 0;
        Arrays.sort(satisfaction);
        int n = satisfaction.length;
        int res = 0;
        int val = 0;
        // 和那个nested list类似，后面都是反复加上前面选过的。
        for (int i = n - 1; i >= 0 && (val + satisfaction[i] >= 0); i--) {
            val += satisfaction[i];
            res += val;
        }
        return res;
    }
}
