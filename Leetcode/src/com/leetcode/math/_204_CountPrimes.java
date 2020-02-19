package com.leetcode.math;

public class _204_CountPrimes {

    /**
     * 204. Count Primes
     * When: 2019/04/08
     * review1:2019/8/9
     * solution: 厄拉多塞筛法，时间复杂度O(nloglogn)
     * Test case: n = 10
     * i = 2 res++ -> res = 1;
     *       进入for循环 i = 4(2*2), 6(2*3), 8(2*4) 设置为true
     * i = 3 res++ -> res = 2;
     *       进入for循环 i = 6(3*2), 9(3*3) 设置为true
     * i = 4 为true不进入
     * i = 5 res++ -> res = 3;
     *       不进入for循环
     * i = 6 true 不进入
     * i = 7 false res++ -> res = 4
     * i = 8 true 不进入
     * i = 9 true 不进入
     * res = 4
     * @param n
     * @return
     */ 
    // time:O(n * sqrt(n)) 
    public static int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (!notPrime[i]) {
                res++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
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
