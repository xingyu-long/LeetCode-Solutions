package com.leetcode.dynamic_programming;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class _139_WordBreak {
    // https://www.youtube.com/watch?v=RPeTFTKwjps
    // time: O(n^2 ~ n^4) substring:O(n) contains:O(n) space:O(n)
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        // 这个位置决定最远的距离
        for (int i = 1; i <= s.length(); i++) {
            // 这个决定开始的地方
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    // DFS with memo
    // 如何节省重复计算？ 后面的那个部分是可以节约的，如果碰到aaaaaaaaa, indict=["a", "aa", ... ]
    // 应该看成 后面是否在dict中，然后再dfs求解前面的，这样好理解word break II
    // time: (n * n) space:O(n)
    public boolean wordBreak2(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        Map<Integer, Boolean> map = new HashMap<>();
        Set<String> dict = new HashSet<>(wordDict);
        return dfs(s, 0, dict, map);
    }

    public boolean dfs(String s, int index, Set<String> dict, Map<Integer, Boolean> map) {
        if (index == s.length()) return true;
        if (dict.contains(s)) return true;
        if (map.get(index) != null) return map.get(index);

        for (int i = index + 1; i <= s.length(); i++) {
            String word = s.substring(index, i);
            if (dict.contains(word) && dfs(s, i, dict, map)) {
                map.put(index, true);
                return true;
            }
        }
        map.put(index, false);
        return false;
    }

    // BFS
    // https://leetcode.com/problems/word-break/discuss/43797/A-solution-using-BFS
    public static boolean wordBreak3(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        Set<String> dict = new HashSet<>();
        for (String word : wordDict) {
            dict.add(word);
        }
        return bfs(s, dict);
    }

    // 这个的思路和DP一样，找出第一个符合的字串，然后从那里出发，继续找
    // 从第一个出发，然后找到第一个符合的单词，之后继续出发
    // DP是在每次的i范围中找catsandog来理解 continue跳过了哪些。
    // 利用那个""
    public static boolean bfs(String s, Set<String> dict) {
        if (dict.contains(s)) return true;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);//后面是相当于从0开始截取字符串
        Set<Integer> used = new HashSet<>(); // 用来防止重复计算。
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (int i = cur + 1; i <= s.length(); i++) {
                if (used.contains(i)) continue;
                if (dict.contains(s.substring(cur, i))) {
                    // System.out.println("i = "+ i);
                    if (i == s.length()) return true;
                    used.add(i);
                    queue.offer(i);
                }
            }
        }
        return false;
    }
}
