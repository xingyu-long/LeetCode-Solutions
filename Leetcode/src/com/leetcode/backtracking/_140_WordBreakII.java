package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _140_WordBreakII {
    /**
     * 140. Word Break II
     * When: 2019/05/03
     * Review1: 2019/7/31
     * <p>
     * solution:
     * 类似于word break里面的规则，但是使用具有memory的DFS算法求解
     *
     * @param s
     * @param wordDict
     * @return
     */
    // time:(2^n) space:(2^n)
    public static List<String> wordBreak(String s, List<String> wordDict) {
        HashMap<Integer, List<String>> map = new HashMap<>();
        return dfs(s, wordDict, 0, map);
    }

//    https://www.youtube.com/watch?v=pYKGRZwbuzs
    public static List<String> dfs(String s, List<String> wordDict, int start, HashMap<Integer, List<String>> map) {
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

    public static void main(String[] args) {

        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        List<String> res = wordBreak(s, wordDict);
        for (String string : res) {
            System.out.println(string);
        }
    }
}
