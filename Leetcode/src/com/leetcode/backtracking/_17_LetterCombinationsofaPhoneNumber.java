package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _17_LetterCombinationsofaPhoneNumber {
    /**
     * 17. Letter Combinations of a Phone Number
     * When: 2019/05/02
     * review1:2019/10/14
     * solution:
     * 相当于是
     * digits = "23"
     *
     *         /  d
     * i = 0 a  - e
     *         \  f
     *
     * i = 1 b ....
     * i = 2 c ....
     *
     * time: O(3^n * 4^m) 这里的n是在按键为3个字母的情况下 m是四个字母的情况下
     * space: O(3^n * 4^m)
     *
     * @param digits
     * @return
     */

    private String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

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
}
