package com.leetcode.math;

/**
 * _365_WaterandJugProblem
 */
public class _365_WaterandJugProblem {
    // https://leetcode.com/problems/water-and-jug-problem/discuss/83715/Math-solution-Java-solution
    public boolean canMeasureWater(int x, int y, int z) {
        if (x + y < z) return false;
        if (x == z || y == z || x + y == z) return true;

        return z % gcd(x, y) == 0;
    }

    // recursion. 
    public static int gcd(int x, int y) {
        if (y == 0) return x;
        return gcd(y, x % y);
    }
    // iterative
    public static int gcd2(int x, int y) {
        while (y != 0) {
            int temp = x;
            x = y;
            y = temp % y;
        }
        return x;
    }
}