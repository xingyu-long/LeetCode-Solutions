package com.leetcode.math;

import java.util.Arrays;

public class _204_CountPrimes {
    // time:O(n * sqrt(n)) 
    public int countPrimes(int n) {
        boolean[] prime = new boolean[n];
        Arrays.fill(prime, true);
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (prime[i]) {
                res++;
                for (int j = 2; j * i < n; j++) {
                    prime[j * i] = false;
                }
            }
        }
        return res;
    }

    // brute force, time:O(n * n) space:O(1)
    public int countPrimes2(int n) {
        if (n < 1) return 0;
        int res = 0;
        for (int i = 2; i < n; i++) {
            int j;
            for (j = 2; j < i; j++) {
                if (i % j == 0) break;
            }
            if (j == i) res++;
        }
        return res;
    }
}
