package com.leetcode.math;

import java.util.HashMap;

public class _509_FibonacciNumber {

    /**
     *  509. Fibonacci Number
     *  When:2019/7/23
     *  Difficulty: Easy
     *
     * @param N
     * @return
     */
    //time:O(1) space:O(1)
    public int fib(int N) {
        if (N < 2) {
            return N;
        }
        return fib(N - 1) + fib(N - 2);
    }

    HashMap<Integer, Integer> map = new HashMap<>();
    //recursion with memorization
    // time:O(n) space:O(n)
    public int fib2(int N) {
        if (map.containsKey(N)) {
            return map.get(N);
        }
        int res;
        if (N < 2) {
            res = N;
        } else {
            res = fib2(N - 1) + fib2(N - 2);
        }
        map.put(N, res);
        return res;
    }
}
