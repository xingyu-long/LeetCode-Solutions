package com.leetcode.string;

public class _168_ExcelSheetColumnTitle {

    /**
     * 168. Excel Sheet Column Title
     * When: 2019/04/01
     *
     * 涉及到的数据结构
     * StringBuilder, sb.reverse()
     *
     * solution:
     * 主要是找规律
     * Test case:
     * n = 5
     *      n -- = 4;
     *      sb = "E"
     *      n = n / 26 = 0 （由于这里是int 所以就会为0）
     * n = 48
     *      n -- = 47
     *      sb = "V"
     *      n / 26 = 2
     *    n-- = 0
     *      sb = "VA"
     *      n / 26 = 0
     *    res -> "AV"
     * @param n
     * @return
     */
    public static String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char)('A' + n % 26));
            n /= 26; //用来记录第一个字母（即最前面的那个
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println((int)(47/26));
        System.out.println(convertToTitle(48));
        return;
    }
}
