package com.leetcode.stackPriorityQueue;

public class _313_SuperUglyNumber {

    /**
     * 313. Super Ugly Number
     * When:2019/9/5
     * Difficulty: Medium
     * @param n
     * @param primes
     * @return
     */
    // 利用和ugly number II 一样的思路
    // time: O(kn) space:O(n)
    public static int nthSuperUglyNumber(int n, int[] primes) {
        if (primes == null || primes.length == 0) return 0;
        int[] res = new int[n];
        int[] index = new int[primes.length];// 表示从哪个res开始计算。就是后面的primes[j] * res[index[j]];

        res[0] = 1;
        for (int i = 1; i < n; i++) {
            // find the next ugly number
            res[i] = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                res[i] = Math.min(res[i], primes[j] * res[index[j]]);
            }

            // 用来帮助后面与prime相乘,因为我们需要计算当前的res
            for (int j = 0; j < primes.length; j++) {
                while (primes[j] * res[index[j]] <= res[i]) {
                    index[j]++;
                }
            }
        }
        return res[n - 1];
    }

    public static void main(String[] args) {
        int n = 6;
        int[] primes = new int[]{2,7,13,19};
        nthSuperUglyNumber(n, primes);
    }
}