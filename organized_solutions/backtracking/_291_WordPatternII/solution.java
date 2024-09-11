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
    // 这里根本也没有memo，主要还是建立的对应关系。
    public static boolean wordPatternMatch(String pattern, String str) {
        HashMap<Character, String> map = new HashMap<>();
        HashSet<String> set = new HashSet<>();
        return isMatch2(str, 0, pattern, 0, map);
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
        } else {
            for (int k = i; k < str.length(); k++) {
                String p = str.substring(i, k + 1);
                if (set.contains(p)) continue; // 表示尽管没有这个key但这个value被占用了，所以不能用这个。
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
    }

    // 可以加入set来优化一下。
    private static boolean isMatch2(String str, int startOfStr, String pattern, int startOfPatt, HashMap<Character, String> map) {
        if (startOfPatt == pattern.length() && startOfStr == str.length()) return true;
        if (startOfPatt == pattern.length() || startOfStr == str.length()) return false;
        char ch = pattern.charAt(startOfPatt);
        if (map.containsKey(ch)) {
            String s = map.get(ch);
            if (!str.startsWith(s, startOfStr)) return false;
            return isMatch2(str, startOfStr + s.length(), pattern, startOfPatt + 1, map);
        } else {
            for (int i = startOfStr + 1; i <= str.length(); i++) {
                String val = str.substring(startOfStr, i);
                if (map.containsValue(val)) continue;
                map.put(ch, val);
                if (isMatch2(str, i, pattern, startOfPatt + 1, map)) return true;
                map.remove(ch);
            }
        }
        return false;
    }
    // 需要解释的是，如果是ab -> abcabc 这个return true因为可能a -> ab b->cabc, 这也是为什么上面判断有value存在之后用的continue而不是直接false
    // ab -> aa 的话就会return false 因为会跳过下一个a所以只能false。
    public static void main(String[] args) {
        String pattern = "ab";
        String str = "aa";
        System.out.println(wordPatternMatch(pattern, str));
    }
}