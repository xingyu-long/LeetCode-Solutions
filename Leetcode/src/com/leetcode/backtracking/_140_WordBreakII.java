package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _140_WordBreakII {

    static HashMap<Integer, List<String>> map = new HashMap<>();

    /**
     *  140. Word Break II
     *  When: 2019/05/03
     *  Review1: 2019/7/31
     *
     * solution:
     * 类似于word break里面的规则，但是使用具有memory的DFS算法求解
     * @param s
     * @param wordDict
     * @return
     */
    // 这个时间复杂度，空间复杂度目前不知。
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
                // 这一部分用的太妙了，从最后的res = ""开始然后倒着循环出结果
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
