package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _140_WordBreakII {

    static HashMap<Integer, List<String>> map = new HashMap<>();

    /**
     * 140. Word Break II
     * When: 2019/05/03
     *
     * solution:
     * 这个的方法 我认为不像是在回溯里面 其中有部分类似于word break里面的 dp算法
     * @param s
     * @param wordDict
     * @return
     */
    public static List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, 0);
    }

    public static List<String> dfs(String s, List<String> wordDict, int start) {
        if (map.containsKey(start)) {
            return map.get(start);
        }

        List<String> res = new ArrayList<>();
        if (start == s.length()) {
            res.add("");
        }

        for(int end = start + 1; end <= s.length(); end++) {
            if (wordDict.contains(s.substring(start, end))) {
                List<String> list = dfs(s, wordDict, end);
                for (String temp : list) {
                    res.add(s.substring(start, end) + (temp.equals("") ? "" : " ") + temp);
                }
            }
        }
        map.put(start, res);
        return res;
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
