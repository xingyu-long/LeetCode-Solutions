package com.leetcode.backtracking;

import java.util.HashMap;
import java.util.HashSet;

public class _291_WordPatternII {

    /**
     * 291. Word Pattern II
     * Difficulty: Hard
     * time:2019/10/14
     * solution: 虽然是很直观的暴力解法，但有些还是不太理解。。。需要过几天再看
     * @param pattern
     * @param str
     * @return
     */
    public static boolean wordPatternMatch(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
//        return isMatch(str, 0, pattern, 0, map, set);
        return dfs(pattern, str, map, set, 0, 0);
    }

    public static boolean dfs(String pattern, String str, HashMap<Character,
            String> map, HashSet<String> set, int i, int j) {
        if (i == str.length() && j == pattern.length()) return true;
        if (i == str.length() || j == pattern.length()) return false;

        char ch = pattern.charAt(j);
        if (map.containsKey(ch)) {
            String s = map.get(ch);
            if (!str.startsWith(s, i)) {
                return false;
            }
            return dfs(pattern, str, map, set, i + s.length(), j + 1);
        }
        for (int k = i; k < str.length(); k++) {
            String temp = str.substring(i, k + 1);
            if (set.contains(temp)) {
                continue;
            }
            map.put(ch, temp);
            set.add(temp);
            if (dfs(pattern, str, map, set, k + 1, j + 1)) {
                return true;
            }
            map.remove(ch);
            set.remove(temp);
        }
        return false;
    }

    private static boolean isMatch(String str, int i, String pat, int j, HashMap<Character, String> map, HashSet<String> set) {
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;

        char c = pat.charAt(j);
        if (map.containsKey(c)) {
            String s = map.get(c);
            if (!str.startsWith(s, i)) {
                return false;
            }
            return isMatch(str, i + s.length(), pat, j + 1, map, set);
        }

        for (int k = i; k < str.length(); k++) {
            String p = str.substring(i, k + 1);
            if (set.contains(p)) {
                continue;
            }
            map.put(c, p);
            set.add(p);
            if (isMatch(str, k + 1, pat, j + 1, map, set)) {
                return true;
            }
            map.remove(c);
            set.remove(p);
        }
        return false;
    }

    public static void main(String[] args) {
        String pattern = "aba";
        String str = "redblueblue";
        System.out.println(wordPatternMatch(pattern, str));
    }
}