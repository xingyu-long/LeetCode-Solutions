package com.leetcode.string;

public class _345_ReverseVowelsofaString {

    /**
     *  345. Reverse Vowels of a String
     *  When:2019/7/17
     *  review1: 2019/8/24
     *  Difficulty: Easy
     *
     * @param s
     * @return
     */
    //time:O(n) space:O(n)
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) return s;
        String vowels = "aeiouAEIOU";
        char[] array = s.toCharArray(); //需要记一下
        int lo = 0;
        int hi = s.length() - 1;
        while (lo < hi) {
            while (lo < hi && vowels.indexOf(array[lo]) == -1) {
                lo++; //直到找到元音字母
            }
            while (lo < hi && vowels.indexOf(array[hi]) == -1) {
                hi--; //直到找到元音字母
            }
            char temp = array[lo];
            array[lo] = array[hi];
            array[hi] = temp;
            // for the while loop
            lo++;
            hi--;
        }
        return String.valueOf(array);
    }
}
