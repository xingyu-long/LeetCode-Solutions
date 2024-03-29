package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Date: 2019/05/03, 2019/7/31, 04/24/2020
 * @Description: DP, DFS
 **/
public class _140_WordBreakII {

    // time:(n^2) space:(2^n)
    public static List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        return dfs(s, wordDict, 0, map);
    }

    //    https://www.youtube.com/watch?v=pYKGRZwbuzs
    public static List<String> dfs(String s, List<String> wordDict, int start,
        HashMap<Integer, List<String>> map) {
        if (map.containsKey(start)) {
            return map.get(start);
        }

        List<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add("");
            return res;
        }

        for (int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                // 这一部分用的太妙了，从最后的res = ""开始然后倒着循环出结果
                List<String> list = dfs(s, wordDict, end, map);
                for (String temp : list) {
                    res.add(s.substring(start, end) + (temp.equals("") ? "" : " ") + temp);
                }
            }
        }
        map.put(start, res);
        return res;
    }

    // 典型的backtracking，但是这里无法做memo，因为一个字符串会有多个结果
    public void dfs2(List<String> res, String s, String cur, List<String> dict, int index) {
        if (index == s.length()) {
            res.add(new String(cur));
            return;
        }
        for (int i = index + 1; i <= s.length(); i++) {
            if (dict.contains(s.substring(index, i))) {
                cur += s.substring(index, i) + (i == s.length() ? "" : " ");
                dfs2(res, s, cur, dict, i);
                cur = cur.substring(0, cur.length() - (i - index) - (i == s.length() ? 0 : 1));
            }
        }
    }

    public List<String> wordBreak3(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) {
            return new ArrayList<>();
        }
        Set<String> dict = new HashSet<>(wordDict);
        return build(s, dict, 0);
    }
    
    public List<String> build(String s, Set<String> dict, int index) {
        List<String> res = new ArrayList<>();
        if (dict.contains(s.substring(index))) {
            res.add(s.substring(index));
        }
        
        for (int end = index + 1; end <= s.length(); end++) {
            String sub = s.substring(index, end);
            if (dict.contains(sub)) {
                List<String> next = build(s, dict, end);
                for (String temp : next) {
                    res.add(sub + " " + temp);
                }
            }
        }
        return res;
    }
}
