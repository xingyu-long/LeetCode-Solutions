package com.leetcode.math;

public class _8_StringToInteger {

    /**
     *  8. String to Integer (atoi)
     *  when: 2019/04/04
     *  Review1:2019/7/21
     *
     * 涉及到的数据结构以及方法
     * Character.isDigit(str.charAt(i))
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        // 考虑三种情况 1. 前面的 '+'/'-' 2. 无效的情况（一开始的字符是不digits）3. 数值越界问题
        // 先取出所有的空格 这个是错误的！
        // 只能取出前面的空格！！！因为这样中间如果出现 是直接表示不存在该数字的！
        str = str.trim();
        if (str == null || str.length() == 0) return 0;
        int sign = 1;
        long res = 0;
        int start = 0;
        char firstChar = str.charAt(0);
        if (firstChar == '+') {
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i <  str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            // 要判断越界的问题
            res = res * 10 + str.charAt(i) - '0';
            if (sign == -1 && (-1) * res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
            if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        }
        return (int) res * sign;
    }
}
