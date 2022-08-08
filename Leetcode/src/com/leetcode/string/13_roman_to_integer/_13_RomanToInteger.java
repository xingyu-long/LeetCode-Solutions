package com.leetcode.string;

public class _13_RomanToInteger {

    /**
     * 13. Roman to Integer
     * When: 2019/04/01
     * Difficulty: 2019/8/8
     * 之前的做法就是每次相加，然后扫描，符合情况的减去 2 * 小的那个数
     * 规律：左边的数字小于右边的数字 = 右 - 左
     * Test case:
     * "MCMXCIV" (M 1000, C 100, X 10, V 5, I 1)
     *  res = "M" = 1000
     *  i = 1 res = 1000 + 100
     *  i = 2 res = 1000 + 100 + 1000 - 2 * 100 （这里满足后一个数 大于 前一个数） 减去2个100 这样可以消除前面res多算的100
     *  i = 3 res = 1000 + 100 + 1000 - 2 * 100 + 10
     *  i = 4 res = 1000 + 100 + 1000 - 2 * 100 + 10 + 100 - 2 * 10
     *  i = 5 res = 1000 + 100 + 1000 - 2 * 100 + 10 + 100 - 2 * 10 + 1
     *  i = 6 res = 1000 + 100 + 1000 - 2 * 100 + 10 + 100 - 2 * 10 + 1 + 5 - 1 * 2 = 1994
     * @param s
     * @return
     */
    // time:O(n) space:O(1)
    public int romanToInt(String s) {
        // 循环相加，如果遇到左边的数字小于右边的数字则直接相减（由于一开始的res等于第一个数，所以后面相减的时候要减去才对）
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        res += toNumber(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            int before = toNumber(s.charAt(i - 1));
            int after = toNumber(s.charAt(i));
            res += after;
            if (before < after) {
                res -= 2 * before;
            }
        }
        return res;
    }

    // 用来转换成数字
    public static int toNumber(char c) {
        int res = 0;
        switch (c) {
            case 'I' : return 1;
            case 'V' : return 5;
            case 'X' : return 10;
            case 'L' : return 50;
            case 'C' : return 100;
            case 'D' : return 500;
            case 'M' : return 1000;
        }
        return res;
    }


}
