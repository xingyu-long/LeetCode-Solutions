package com.leetcode.string.Palindrome;

import java.util.ArrayList;
import java.util.List;

import static com.leetcode.string.Palindrome._336_PalindromePairs.isPalindrome;

public class _131_PalindromePartitioning {

    /**
     * 131. Palindrome Partitioning
     * when:2019/8/9
     * Difficulty: Medium
     * solution: backtracking
     * @param s
     * @return
     */
    // time: 这个后面再看 space:O(n)
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0) return res;
        helper(s, res, new ArrayList<>());
        return res;
    }

    public void helper(String s, List<List<String>> res, List<String> list) {
        if (s.length() == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (isPalindrome(s.substring(0, i + 1))) {
                list.add(s.substring(0, i + 1));
                helper(s.substring(i + 1), res, list);
                list.remove(list.size() - 1);
            }
        }
    }

}
