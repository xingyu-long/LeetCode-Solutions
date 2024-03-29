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

    // time: O(sqrt(n)loglogN)
    public int countPrimes2(int n) {
        if (n <= 2) {
            return 0;
        }
        boolean[] member = new boolean[n];
        Arrays.fill(member, false);
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (member[i] == false) {
                for (int j = 2; i * j < n; j++) {
                    member[i * j] = true;
                }
            }
        }
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (member[i] == false) {
                res++;
            }
        }
        return res;
    }
}
