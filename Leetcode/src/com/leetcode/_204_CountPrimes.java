package com.leetcode;

public class _204_CountPrimes {

    /**
     * 204. Count Primes
     * When: 2019/04/08
     *
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
    public static int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int res = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                res++;
                for (int j = 2; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        countPrimes(10);
    }
}
