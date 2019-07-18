package com.leetcode.math;

public class _367_ValidPerfectSquare {

    /**
     * 367. Valid Perfect Square
     * When: 2019/7/18
     * Difficulty: Easy
     *
     * @param num
     * @return
     */

    //time: O(sqrt(n)) space:O(1)
    public static boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        // 后半段用不上
        for (int i = 1; i <= num / i; i++) {
            if (i * i == num) return true;
        }
        return false;
    }

    // binary search time: O(logn) space:O(1)
    // 注意使用long来保持 (why？？？)
    // mid * mid 可能出现越界的情况
    public static boolean isPerfectSquare2(int num) {
        int lo = 1;
        int hi = num;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                lo =  (int) mid + 1;
            } else {
                hi =  (int) mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(isPerfectSquare2(2147483647));
    }
}

