package com.leetcode.math;

/**
 * CTCI_RecursiveMultiply
 */
public class CTCI_RecursiveMultiply {

    public static int product(int a, int b) {
        if (a < b) return helper(a, b);
        else return helper(b, a);
    }

    public static int helper(int smaller, int bigger) {
        if (smaller == 0) return 0; // 如果是负数的话，就需要这个check 0
        else if (smaller == 1) return bigger;
        int half = helper(smaller / 2, bigger);
        if (smaller % 2 == 0) {
            return half + half;
        } else {
            return half + bigger + half;
        }
    }

    public static void main(String[] args) {
        System.out.println(product(7, 8));
    }
}