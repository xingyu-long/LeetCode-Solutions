package com.leetcode.dynamicProgramming.gametheory;

import java.util.HashMap;
import java.util.Map;

public class _1140_StoneGameII {
    public int stoneGameII(int[] piles) {
        if (piles == null || piles.length == 0) {
            return 0;
        }
        int n = piles.length;
        int[] prefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] + piles[i - 1];
        }
        Map<String, Integer> map = new HashMap<>();
        int diff = dfs(piles, 0, 1, prefix, map);
        return (prefix[n] + diff) / 2;
    }
    
    public int dfs(int[] piles, int index, int M, int[] prefix, Map<String, Integer> map) {
        if (index >= piles.length) {
            return 0;
        }
        if (index + 2 * M >= piles.length) {
            return prefix[piles.length] - prefix[index];
        }
        String key = index + "_" + M;
        if (map.containsKey(key)) return map.get(key);
        
        int res = Integer.MIN_VALUE;
        // number of piles we can use
        for (int i = 1; i <= 2 * M; i++) {
            int sum = prefix[index + i] - prefix[index];
            res = Math.max(res, sum - dfs(piles, index + i, Math.max(M, i), prefix, map));
        }
        map.put(key, res);
        return res;
    }
}
