package com.leetcode;

public class _171_ExcelSheetColumnNumber {

    /**
     * 171. Excel Sheet Column Number
     *  类似于168
     *  可以考虑为26进制
     *
     *  Test case:
     *  String = "BA"
     *      res = 0;
     *      i = 0; res = 0 * 26 + ('B' - 'A' + 1) = 2 （这个其实就是表示前面的循环过多少个26 这里的B 则就是前面有A,B... Z这个循环
     *      以及AA, AB...AZ的循环）
     *      i = 1; res = 2 * 26 + ('A' - 'A' + 1) = 53
     *      res = 53
     * @param s
     * @return
     */
    public int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            res = res * 26 + (s.charAt(i) - 'A' + 1);
        }
        return res;
    }
}
