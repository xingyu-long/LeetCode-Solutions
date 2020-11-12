package com.leetcode.string;

public class _161_OneEditDistance {
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


    public static boolean isOneEditDistance2(String s, String t) {
        int diff = Math.abs(s.length() - t.length());
        if (diff >= 2) return false;
        if (diff == 1) { // 本来就差1，需要add或者delete，这时候还有不同的就直接false；
            for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
                if (s.charAt(i) != t.charAt(i)) return false;
            }
        } else { // diff = 0;
            int count = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != t.charAt(i)) count++;
            }
            return count == 1;
        }
        return true;
    }

    // 简化的写法。这个只是个人写法，但我觉得可行。
    public static boolean isOneEditDistance3(String s, String t) {
        int diff = Math.abs(s.length() - t.length());
        if (diff >= 2) return false;
        for (int i = 0; i < Math.min(s.length(), t.length()); i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (s.length() == t.length()) {
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
