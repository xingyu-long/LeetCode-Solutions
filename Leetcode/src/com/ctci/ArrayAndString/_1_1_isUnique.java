package com.ctci.ArrayAndString;

public class _1_1_isUnique {
    /**
     * 2019/8/18
     *
     * Implement an algorithm to determine if a string has all unique characters.
     * What if you can not use additional data structures?
     *
     * solution:
     * 1. hash来看是否有重复
     * 2. 使用位运算，
     * @param s
     * @return
     */
    // 使用数组hash
    // time:O(n) space:O(1) 128 is constant
    public static boolean isUnique(String s) {
        if (s.length() > 128) {
            return false; // 字符长度超过128，肯定会有重复的情况
        }
        boolean[] hash = new boolean[128];
        for (int i = 0; i < s.length(); i++) {
            if (hash[s.charAt(i)]) {
                return false;// 表示以前已经存在了
            }
            hash[s.charAt(i)] = true;
        }
        return true;
    }

    // don't use any structure
    // Bit manipulation 利用位运算来看在当前位是否已经为1
    // time:O(n) space:O(1)
    public static boolean isUnique2(String s) {
        int check = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a';
            if ((check & (1 << val)) > 0) {
                return false; // 1 << val 表示当前val移动的位置就是val的位置
            }
            check |= (1 << val);
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isUnique2("abbde"));
    }
}
