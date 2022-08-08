package com.leetcode.math;

public class _372_SuperPow {

    public static int superPow(int a, int[] b) {
        return helper(a, b, b.length, 1337);
    }

    private static int helper(int a, int[] b, int length, int k) {
        if (length == 1) {
            return powMod(a, b[0], k);
        }
        return powMod(helper(a, b, length - 1, k), 10, k) * powMod(a, b[length - 1], k) % k;
    }

    private static int powMod(int x, int y, int k) {
        x %= k;
        int pow = 1;
        for (int i = 0; i < y; i++) {
            pow = pow * x % k;
        }
        return pow;
    }

    public static void main(String[] args) {
        int a = 2;
        int[] b = {1, 0, 0};
        superPow(a, b);
    }
}
