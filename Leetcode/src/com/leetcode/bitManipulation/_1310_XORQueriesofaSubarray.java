package com.leetcode.bitManipulation;

public class _1310_XORQueriesofaSubarray {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int n = arr.length;
        int[] xorPrefix = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            xorPrefix[i] = xorPrefix[i - 1] ^ arr[i - 1];
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];
            res[i] = xorPrefix[query[0]] ^ xorPrefix[query[1] + 1];
        }
        return res;
    }
}
