package com.leetcode.range_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 05/11/2020
 * @Description: Range XOR,
 **/
public class _1442_CountTripletsThatCanFormTwoArraysofEqualXOR {

    // https://www.bilibili.com/video/BV17g4y1B7yo
    // time:O(N^3) space:O(n)
    public int countTriplets(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        int res = 0;
        int[] prefix = new int[n + 1];
        prefix[0] = 0;
        //         a,   b,      c,         d,        e,         f
        //    0, 0^a, 0^a^b, 0^a^b^c, 0^a^b^c^d, 0^a^b^c^d^e, 0^a^b^c^d^e^f
        //i:  0  i             j                    k
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] ^ arr[i - 1];
        }
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j; k < n; k++) {
                    if ((prefix[i] ^ prefix[j]) == (prefix[k + 1] ^ prefix[j])) {
                        res++;
                    }
                }
            }
        }
        return res;
    }

    // time:O(n^2) find all pairs (i, k) such that prefix[i] = prefix[k + 1]
    public int countTriplets2(int[] arr) {
        int n = arr.length;
        int res = 0;
        int[] prefix = new int[n + 1];
        prefix[0] = 0;
        for (int i = 1; i <= n; i++) {
            prefix[i] = prefix[i - 1] ^ arr[i - 1];
        }
        for (int i = 0; i < n; i++) {
            for (int k = i + 1; k < n; k++) {
                if (prefix[i] == prefix[k + 1]) {
                    res += k - i;
                }
            }
        }
        return res;
    }
    // time:O(n) space:O(n) 类似于target sum
    public int countTriplets3(int[] arr) {
        int n = arr.length;
        int res = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Integer, Integer> sum = new HashMap<>();
        freq.put(0, 1);
        int xor = 0;
        for (int i = 0; i < n; i++) {
            xor ^= arr[i];
            res += freq.getOrDefault(xor, 0) * i - sum.getOrDefault(xor, 0);
            freq.put(xor, freq.getOrDefault(xor, 0) + 1);
            sum.put(xor, sum.getOrDefault(xor, 0) + i + 1);
        }
        return res;
    }
}
