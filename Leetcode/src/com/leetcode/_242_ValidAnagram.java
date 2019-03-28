package com.leetcode;

import java.util.*;

public class _242_ValidAnagram {

    /**
     * 242. Valid Anagram
     * When: 2019/03/28
     *
     * solution：
     * 1. 先转换成字符数组然后sort 比较即可
     * 2. 利用counting sort
     * @param s
     * @param t
     * @return
     */
    public static boolean isAnagram(String s, String t) {
        /**
         * solution1: sort
         if (s.length() != t.length()) {
         return false;
         }
         char[] str1 = s.toCharArray();
         char[] str2 = t.toCharArray();

         // sort
         Arrays.sort(str1);
         Arrays.sort(str2);

         for (int i = 0; i < s.length(); i++){
         if (str1[i] != str2[i]){
         return false;
         }
         }
         return true;
         **/
        /**
         * 利用counting sort
         * 容易出错的地方 1. 坐标会写错 2. if (count[j] != 0) 一开始忘记了 3. 后面计算个数要写上count[j]
         */
        if (s.length() != t.length()) {
            return false;
        }
        List<String> strs = new ArrayList<>();
        strs.add(s);
        strs.add(t);
        List<String> res = new ArrayList<>();
        for (int i = 0; i < strs.size(); i++){
            int[] count = new int[26];
            String temp = "";
            for (Character ch: strs.get(i).toCharArray()) {
                count[ch-'a']++;
            }
            for (int j = 0; j < count.length; j++) {
                if (count[j] != 0) {
                    temp += String.valueOf(count[j]) + String.valueOf((char)(j + 'a'));
                    System.out.println(temp);
                }
            }
            res.add(temp);
        }
        if (res.get(0).equals(res.get(1))) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args){
        System.out.println(isAnagram("aacc", "ccac"));
    }
}
