package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * _PasswordCracker
 */
public class _PasswordCracker {

    // https://www.hackerrank.com/challenges/password-cracker/problem
    public static String passwordCracker(List<String> passwords, String loginAttempt) {
        // Write your code here
        List<String> res = new ArrayList<>();
        HashMap<Integer, Boolean> map = new HashMap<>();
        if (!helper(res, passwords, loginAttempt, 0, map))
            return "WRONG PASSWORD";
        StringBuilder sb = new StringBuilder();
        for (String str : res) {
            sb.append(str + " ");
        }
        return sb.toString().trim();
    }

    // 利用布尔变量进行backtracking，这样可以一旦早就一个情况就停止，要和word break II比较！！！
    public static boolean helper(List<String> res, List<String> passwords, String loginAttempt, int start,
            HashMap<Integer, Boolean> map) {
        if (map.get(start) != null)
            return map.get(start);
        if (start == loginAttempt.length()) {
            res.add("");
            return true;
        }

        for (int end = start + 1; end <= loginAttempt.length(); end++) {
            String word = loginAttempt.substring(start, end);
            if (passwords.contains(word)) {
                res.add(word);
                if (helper(res, passwords, loginAttempt, end, map)) {
                    map.put(start, true);
                    return true;
                }
                res.remove(res.size() - 1);
            }
        }
        map.put(start, false);
        return false;
    }
}