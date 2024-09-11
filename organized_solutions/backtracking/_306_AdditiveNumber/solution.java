package com.leetcode.backtracking;

/**
 * @Date: 2019/8/12, 07/13/2020
 * @Description: backtracking
 **/
public class _306_AdditiveNumber {

    // 其实这个应该是Search, backtracking
    // https://www.youtube.com/watch?v=LziJZT2uRwc
    // 主要也需要确认第一个数和第二个数的位置以及区间
    public boolean isAdditiveNumber(String num) {
        if (num.length() < 3) {
            return false;
        }

        for (int i = 1; i <= num.length() / 2; i++) {
            // 第一个数开头无法是0
            if (num.charAt(0) == '0' && i > 1) {
                return false;
            }
            Long first = Long.valueOf(num.substring(0, i));
            // 相当于第三个数的长度肯定大于两者最大的情况
            for (int j = 1; num.length() - i - j >= Math.max(i, j); j++) {
                // 因为第三个数肯定是大于等于 前面两个大的那个 eg: 1 99 -> 100
                if (num.charAt(i) == '0' && j > 1) {
                    break; // 因为101这种情况，第二个数不可以，但是可以让其当成第一个数
                }
                Long second = Long.valueOf(num.substring(i, i + j));
                if (isValid(first, second, i + j, num)) {
                    return true;
                }
            }
        }
        return false;
    }

    // 需要用long。。。
    public boolean isValid(Long first, Long second, int start, String num) {
        if (start == num.length()) {
            return true;
        }
        // 第三个数
        second = second + first;
        // 第二个数
        first = second - first;
        String sum = second.toString();
        return num.startsWith(sum, start) && isValid(first, second, start + sum.length(), num);
    }

    // 隐含条件：第三位取的数长度至少大于前两个数中长的那个。
}