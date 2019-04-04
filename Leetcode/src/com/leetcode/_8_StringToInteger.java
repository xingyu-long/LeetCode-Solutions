package com.leetcode;

public class _8_StringToInteger {

    /**
     * 8. String to Integer (atoi)
     * when: 2019/04/04
     *
     * 涉及到的数据结构以及方法
     * Character.isDigit(str.charAt(i))
     * @param str
     * @return
     */
    public static int myAtoi(String str) {
        // 考虑三种情况 1. 前面的 '+'/'-' 2. 无效的情况（一开始的字符是不digits）3. 数值越界问题
        str = str.trim(); //去除两边多余的空格
        if (str == null || str.length() == 0) return 0;
        char firstChar = str.charAt(0);
        int sign = 1;
        int start = 0;
        long res = 0;
        if (firstChar == '+') {
            sign = 1;
            start++;
        } else if (firstChar == '-') {
            sign = -1;
            start++;
        }
        for (int i = start; i <  str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return (int) res * sign;
            }
            res = res * 10 + (str.charAt(i) - '0');
            if (sign == 1 && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
            if (sign == -1 && res > Integer.MAX_VALUE) return Integer.MIN_VALUE; //前面都是用的正数 所以如果 sign为1情况 然后就返回Integer.MIN_VALUE
        }
        return (int) res * sign;
    }

    public static void main(String[] args) {
        myAtoi(" ");
    }
}
