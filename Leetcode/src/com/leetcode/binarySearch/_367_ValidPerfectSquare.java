package com.leetcode.binarySearch;

/**
 * @Date: 2019/7/18, 05/09/2020
 * @Description: binary search
 **/
public class _367_ValidPerfectSquare {

    //time: O(sqrt(n)) space:O(1)
    public static boolean isPerfectSquare(int num) {
        if (num == 1) {
            return true;
        }
        // 后半段用不上
        for (int i = 1; i <= num / i; i++) {
            if (i * i == num) {
                return true;
            }
        }
        return false;
    }

    // binary search time: O(logn) space:O(1)
    // 注意使用long来保持 (why？？？)
    // mid * mid 可能出现越界的情况，并且long可以直接和int比较
    public static boolean isPerfectSquare2(int num) {
        int lo = 1;
        int hi = num;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid < num) {
                lo = (int) mid + 1;
            } else {
                hi = (int) mid - 1;
            }
        }
        return false;
    }

    // 这样也不用long来保存值，并且不用担心取整的问题
    public boolean isPerfectSquare3(int num) {
        int left = 0;
        int right = num;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (mid >= num / mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (left * left == num) {
            return true;
        }
        if (right * right == num) {
            return true;
        }
        return false;
    }
}

