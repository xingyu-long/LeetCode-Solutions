package com.leetcode;

public class _14_LongestCommonPrefix {

    /**
     * LeetCode No.14 Longest Common Prefix
     * When: 2019/03/12
     * 解题思路：
     * 1. 首先选中第一个作为子串，
     * 2. 使用后面的循环str[i]是否包含（使用indexOf）
     * 3. 不包含就减少一位 （这里要用while 而不是if）
     * 涉及到的数据结构或者方法： substring(), indexOf()
     *
     * 疑问： 为什么是！=0 难道相同也会被删除？   因为是求共同最长前缀！！！  并非求最长共同元素
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length <= 0 || strs == null) return "";
        String res = strs[0];

        for (int i = 1; i < strs.length; i++){
            while (strs[i].indexOf(res) != 0){
                res = res.substring(0, res.length() - 1);
            }
        }
        return res;
    }
    public static void main(String[] args){
        System.out.println(longestCommonPrefix(new String[]{"bc","bcd"}));
//        String a = "abc";
//        String b = "bc";
//        System.out.println(a.indexOf(b));
    }
}
