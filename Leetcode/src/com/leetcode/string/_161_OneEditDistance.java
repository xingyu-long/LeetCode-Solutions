package com.leetcode.string;

public class _161_OneEditDistance {

    /**
     *  161. One Edit Distance
     *  When:2019/8/6
     *  Difficulty: Medium
     * @param s
     * @param t
     * @return
     */
    //https://www.cnblogs.com/grandyang/p/5184698.html
    // time:O(n) space:O(1)
    public static boolean isOneEditDistance(String s, String t) {
        if (Math.abs(s.length() - t.length()) > 2) return false;
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
}
