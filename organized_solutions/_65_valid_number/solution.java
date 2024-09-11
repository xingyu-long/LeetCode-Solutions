package com.leetcode;

public class _65_ValidNumber {

    /**
     * 65. Valid Number
     * When: 2019/04/08
     *
     * solution: 列出尽可能多的情况
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        s = s.trim();
        boolean numberSeen = false;
        boolean pointSeen = false;
        boolean eSeen = false; // 记录e的情况
        boolean numberAfterE = true; // 记录e后面是否有数字

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                // 判断数字符合的范围
                numberSeen = true;
                numberAfterE = true;
            } else if (s.charAt(i) == '.') {
                //如果出现了e或者之前有"." 就不行
                if (eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if (s.charAt(i) == 'e') {
                // 如果e已经存在或者前面没有看见数字 则不行
                if (eSeen || !numberSeen) {
                    return false;
                }
                eSeen = true;
                numberAfterE = false;
            } else if (s.charAt(i) == '+' || s.charAt(i) == '-') {
                // 如果加减号不在首位 并且前面的那个不是e的话 就不可以
                if (i != 0 && s.charAt(i - 1) != 'e') {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return numberSeen && numberAfterE;
    }
}
