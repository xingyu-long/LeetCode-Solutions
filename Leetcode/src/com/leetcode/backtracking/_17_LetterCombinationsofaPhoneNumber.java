/*
 * @Date: 08/11/2020 18:07:14
 * @LastEditTime: 04/08/2021 10:10:33
 * @Description: Backtracking
 */
package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _17_LetterCombinationsofaPhoneNumber {

    // time: O(3^n * 4^m) 这里的n是在按键为3个字母的情况下 m是四个字母的情况下
    // space: O(3^n * 4^m)
    private String[] mapping = new String[] { "0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        dfs(res, digits, "", 0);
        return res;
    }

    public void dfs(List<String> res, String digits, String s, int index) {
        if (index == digits.length()) {
            res.add(s);
            return;
        }
        // 这里没用remove，是因为没有用到list作为中间储存。
        String letters = mapping[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            dfs(res, digits, s + letters.charAt(i), index + 1);
        }
    }

    // 类似于word break II这样的
    public List<String> letterCombinations2(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        String[] strs = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
        return dfs(digits, strs, 0);
    }

    private List<String> dfs(String digits, String[] strs, int index) {
        List<String> res = new ArrayList<>();
        if (index == digits.length()) {
            res.add("");
            return res;
        }
        int pos = digits.charAt(index) - '0';
        String str = strs[pos];
        for (int i = 0; i < str.length(); i++) {
            List<String> list = dfs(digits, strs, index + 1);
            for (String temp : list) {
                res.add(str.charAt(i) + temp);
            }
        }
        return res;
    }
}
