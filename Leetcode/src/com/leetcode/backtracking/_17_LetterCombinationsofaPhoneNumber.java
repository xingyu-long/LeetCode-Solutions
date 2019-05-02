package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _17_LetterCombinationsofaPhoneNumber {

    private String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    /**
     * 17. Letter Combinations of a Phone Number
     * When: 2019/05/02
     *
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
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return res;
        }
        helper(res, digits, "", 0);
        return res;
    }

    public void helper(List<String> res, String digits, String s, int index) {
        if (index == digits.length()) {
            res.add(s);
            return;
        }
        String letters = mapping[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            helper(res, digits, s + letters.charAt(i), index + 1);
        }
    }
}
