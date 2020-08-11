package com.leetcode.string.Palindrome;

import java.util.ArrayList;
import java.util.List;

import static com.leetcode.string.Palindrome._336_PalindromePairs.isPalindrome;

public class _131_PalindromePartitioning {

    /**
     * 131. Palindrome Partitioning when:2019/8/9 Difficulty: Medium solution:
     * backtracking
     * 
     * @param s
     * @return
     */
    // time: 这个后面再看 space:O(n)
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0)
            return res;
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

    public static List<List<String>> partition2(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s == null || s.length() == 0)
            return res;
        helper(res, new ArrayList<>(), s, 0);
        return res;
    }

    // 利用backtracking (每次取的长度是1<=n) 然后利用index来判断是否走到了终点
    public static void helper(List<List<String>> res, List<String> list, String s, int start) {
        if (start >= s.length()) {
            res.add(new ArrayList<>(list));
        }
        for (int i = start + 1; i <= s.length(); i++) {
            String temp = s.substring(start, i);
            if (isValid(temp)) { // 有效了我再进去下一层。
                list.add(temp);
                helper(res, list, s, i);
                list.remove(list.size() - 1);
            }
        }
    }

    public static boolean isValid(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aab";
        List<List<String>> res = partition2(s);
        for (List<String> in : res) {
            for (String str : in) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
