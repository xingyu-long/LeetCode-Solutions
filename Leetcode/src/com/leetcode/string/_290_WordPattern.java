package com.leetcode.string;

import java.util.HashMap;

public class _290_WordPattern {

    /**
     *  290.Word Pattern
     *  When:2019/7/17
     *  review1: 2019/8/24
     *  same as LeetCode 205
     *  Difficulty: Easy
     * @param pattern
     * @param str
     * @return
     */
    // time:O(n) space:O(n)
    public boolean wordPattern(String pattern, String str) {
        //如何做映射关系？？ 使用HashMap
        char[] patternArr = pattern.toCharArray();
        String[] strArr = str.split(" ");
        if (patternArr.length != strArr.length) return false;
        HashMap<Character, String> map = new HashMap<>();
        for (int i = 0; i < strArr.length; i++) {
            if (map.containsKey(patternArr[i])) {
                if (!map.get(patternArr[i]).equals(strArr[i])) {
                    return false;
                }
            } else {
                // 使用containsValue来验证是否用过了
                if (!map.containsValue(strArr[i])) {
                    map.put(patternArr[i], strArr[i]);
                } else return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "123";
        s += 'a';
        System.out.println(s);
    }
}
