package com.leetcode.backtracking;

import java.util.*;

/**
 * _StoneDivision
 */
public class _StoneDivision {
    // 至少自己的思路是work的！！！加油加油！
    // https://www.hackerrank.com/challenges/stone-division-2/problem
    static long stoneDivision(final long n, final long[] s) {
        final HashMap<Long, Long> map = new HashMap<>();
        return helper(n, s, map);
    }

    static long helper(final long sizeOfPile, final long[] querySet, final HashMap<Long, Long> map) {
        if (map.get(sizeOfPile) != null)
            return map.get(sizeOfPile);
        if (sizeOfPile == 0 || sizeOfPile == 1)
            return 0;
        long max = 0;
        for (final long query : querySet) {
            long sum = 0;
            if (sizeOfPile % query != 0 || sizeOfPile == query) {
                continue;
            }
            final long numOfPiles = sizeOfPile / query;
            sum += helper(query, querySet, map) * numOfPiles;
            sum += 1;
            max = Math.max(max, sum);
        }
        map.put(sizeOfPile, max);
        return max;
    }
}