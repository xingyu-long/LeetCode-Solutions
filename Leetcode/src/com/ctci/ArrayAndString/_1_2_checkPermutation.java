package com.ctci.ArrayAndString;


import java.util.Arrays;

public class _1_2_checkPermutation {

    /**
     * 2019/8/18
     *
     * Check Permutation: Given two strings,
     * write a method to decide if one is a permutation of the other.
     *
     * solution:
     * 1. 看组成是否相同，利用hash
     * 2. 利用排序
     * @param s1
     * @param s2
     * @return
     */
    // time:O(nlogn)
    public boolean isPermutation(String s1, String s2) {
        return sort(s1).equals(sort(s2));
    }

    public String sort(String s) {
        char[] charS = s.toCharArray();
        Arrays.sort(charS);
        return new String(charS);
    }

    // time:O(n) space:O(1)
    public boolean isPermutation2(String s1, String s2) {
        char[] hash = new char[128];

        for (int i = 0; i < s1.length(); i++) {
            hash[s1.charAt(i)]++;
        }

        for (int i = 0; i < s2.length(); i++) {
            hash[s2.charAt(i)]--;
            if (hash[s2.charAt(i)] < 0) {
                return false;
            }
        }
        return true;
    }
}
