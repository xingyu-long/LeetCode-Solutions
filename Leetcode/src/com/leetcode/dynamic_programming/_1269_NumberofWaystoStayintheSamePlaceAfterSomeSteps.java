package com.leetcode.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 06/05/2020
 * @Description: DFS + memo
 **/
public class _1269_NumberofWaystoStayintheSamePlaceAfterSomeSteps {
    // time: steps * index;
    private int MOD;
    private Map<String, Integer> map;
    public int numWays(int steps, int arrLen) {
        MOD = (int) Math.pow(10, 9) + 7;
        map = new HashMap<>();
        return dfs(0, steps, arrLen);
    }

    private int dfs(int index, int steps, int arrLen) {
        if (index < 0 || index >= arrLen) {
            return 0;
        }
        if (steps == 0) {
            if (index == 0) {
                return 1;
            }
            return 0;
        }
        String key = index + "-" + steps;
        if (map.containsKey(key)) return map.get(key);
        int count = 0;
        count = (count + dfs(index - 1, steps - 1, arrLen)) % MOD;
        count = (count + dfs(index + 1, steps - 1, arrLen)) % MOD;
        count = (count + dfs(index, steps - 1, arrLen)) % MOD;
        map.put(key, count);
        return count;
    }
}
