package com.leetcode.stack_priority_queue;

import java.util.Arrays;
import java.util.PriorityQueue;

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
    // https://leetcode.com/problems/super-ugly-number/discuss/76291/Java-three-methods-23ms-36-ms-58ms(with-heap)-performance-explained
    public int nthSuperUglyNumber(int n, int[] primes) {
        // 如何处理prime这部分是关键。
        int[] index = new int[primes.length];
        long[] res = new long[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            long min = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                min = Math.min(min, res[index[j]] * primes[j]);
            }

            res[i] = min;

            // 需要给达到min的地方加值，每次会想那个2,3,5的例子
            for (int j = 0; j < primes.length; j++) {
                if (res[i] == res[index[j]] * primes[j]) {
                    index[j]++;
                }
            }
        }
        return (int) res[n - 1];
    }

    // time:O(nk) 这个可以减少一定部分的重复计算
    public int nthSuperUglyNumber2(int n, int[] primes) {
        // 如何处理prime这部分是关键。
        int[] index = new int[primes.length];
        int[] res = new int[n];
        int[] val = new int[primes.length];
        Arrays.fill(val, 1);
        int next = 1;

        for (int i = 0; i < n; i++) {
            res[i] = next;

            next = Integer.MAX_VALUE;
            for (int j = 0; j < primes.length; j++) {
                if (val[j] == res[i]) val[j] = res[index[j]++] * primes[j];
                next = Math.min(next, val[j]);
            }
        }
        return res[n - 1];
    }

    // time:O(nlogK)
    public int nthSuperUglyNumber3(int n, int[] primes) {
        int[] res = new int[n];
        PriorityQueue<Num> pq = new PriorityQueue<>();
        for (int prime : primes)
            pq.add(new Num(prime, 1, prime));
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            res[i] = pq.peek().val;
            while (pq.peek().val == res[i]) {
                Num next = pq.poll();
                pq.offer(new Num(next.prime * res[next.index], next.index + 1, next.prime));
            }
        }
        return res[n - 1];
    }

    public class Num implements Comparable<Num> {
        int val;
        int index;
        int prime;

        public Num (int v, int i, int p) {
            val = v;
            index = i;
            prime = p;
        }

        @Override
        public int compareTo(Num o) {
            return this.val - o.val;
        }
    }
}