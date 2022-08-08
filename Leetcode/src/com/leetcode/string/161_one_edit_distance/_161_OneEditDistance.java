/*
 * @Date: 12/05/2019 12:53:09
 * @LastEditTime: 06/06/2022 22:29:35
 * @Description: String, String Matching
 */
package com.leetcode.string;

public class _161_OneEditDistance {
    // https://www.cnblogs.com/grandyang/p/5184698.html
    // time:O(n) space:O(1)
    public static boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 2)
            return false;
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else if (s.length() < t.length()) {
                    return s.substring(i).equals(t.substring(i + 1));
                } else {
                    return s.substring(i + 1).equals(t.substring(i));
                }
            }
        }
        // 前面相等时候，就只能这样判断
        return Math.abs(s.length() - t.length()) == 1;
    }

    public boolean isOneEditDistance2(String s, String t) {
        for (int i = 0, j = 0; i < s.length() && j < t.length(); i++, j++) {
            if (s.charAt(i) != t.charAt(j)) {
               return s.substring(i).equals(t.substring(j + 1)) ||
                   s.substring(i + 1).equals(t.substring(j)) ||
                   s.substring(i + 1).equals(t.substring(j + 1));
            }
        }
    return Math.abs(s.length() - t.length()) == 1;
}
