package com.ctci.ArrayAndString;

public class _1_5_oneAway {

    /**
     * 2019/8/18
     *
     * One Away:
     * There are three types of edits that can be performed on strings: insert a character,
     * remove a character, or replace a character. Given two strings,
     * write a function to check if they are one edit (or zero edits) away.
     *
     * EXAMPLE
     * pale, ple -> true
     * pales, pale -> true
     * pale, bale -> true
     * pale, bae -> false
     *
     * solution:
     * 1. 利用长度差，多个情况分别讨论
     * @param s1
     * @param s2
     * @return
     */
    public static boolean oneEditAway(String s1, String s2) {
        if (s1.length() == s2.length()) {
            return oneEditReplace(s1, s2);
        } else if (s1.length() - 1 == s2.length()) {
            return oneEditInsert(s2, s1);
        } else if (s2.length() - 1 == s1.length()) {
            return oneEditInsert(s1, s2);
        }
        return false;
    }

    public static boolean oneEditReplace(String s1, String s2) {
        boolean diff = false;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diff) { // 表示重复找到了
                    return false;
                }
                diff = true;
            }
        }
        return true;
    }

    // insert a char into s1 to make s2
    public static boolean oneEditInsert(String s1, String s2) {
        int i = 0, j = 0;
        while (i < s1.length() && j < s2.length()) {
            if (s1.charAt(i) != s2.charAt(j)) {
                if (i != j) {
                    return false; // eg: s1: "AB" s2: "ACA"
                }
                j++;
            } else {
                i++;
                j++;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(oneEditAway("AB", "ACC"));
    }
}
