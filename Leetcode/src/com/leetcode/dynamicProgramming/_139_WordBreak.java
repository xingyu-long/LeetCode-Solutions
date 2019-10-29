package com.leetcode.dynamicProgramming;

import java.util.*;
import java.util.stream.StreamSupport;

public class _139_WordBreak {

    /**
     *  139. Word Break
     *  When: 2019/05/03
     *  Review1: 2019/7/31
     *
     * solution:
     * 利用动态规划，转移方程就是dp[j] && wordDict.contains(s.substring(j, i)) 则更新，因为这样就是从上一个有效的dp[j]开始
     * 前一个dp[j] 成立，就是下一个(j,i)的开始
     * test case:
     * s = "leetcode", wordDict = ["leet", "code"]
     * length + 1 = 9
     * dp[0] = true;
     * (1) i = 1
     *    j = 0; j < 1; 不满足contains
     * (2) i = 2
     *    j = 0; j < 2; 不满足contains
     * (3) i = 3
     *    j = 0; j < 3; 不满足contains
     * (4) i = 4
     *    j = 0; j < 4; 当 i = 4的时候 则截取出leet这个词 所以dp[4] = true;
     * (5) i = 5
     *    j = 0; j < 5; 不满足contains
     * (6) i = 6
     *    j = 0; j < 6; 不满足contains
     * (7) i = 7
     *    j = 0; j < 7; 不满足contains
     * (8) i = 8
     *    j = 0; j < 8; 当i = 8的时候 则截取出code这个词语 j = 4 dp[8] = true;
     *
     * 这里也有子问题 则就是 只有当  j = 4 这样的时候才能得到 dp[8]的结果
     * @param s
     * @param wordDict
     * @return
     */
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
    public static boolean wordBreak2(String s, List<String> wordDict) {
        if (s == null || s.length() == 0) return false;
        HashMap<String, Boolean> map = new HashMap<>();
        return dfs(s, wordDict, map);
    }

    public static boolean dfs(String s, List<String> wordDict, HashMap<String, Boolean> map) {
        if (wordDict.contains(s)) return true;
        if (map.containsKey(s)) return map.get(s);
        for (int i = 1; i < s.length(); i++) {
            if (wordDict.contains(s.substring(i)) && dfs(s.substring(0, i), wordDict, map)) {
//                System.out.println(s.substring(0, i));
                map.put(s, true);
                return true;
            }
        }
        map.put(s, false);
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

    // 这个的思路和DP一直，找出第一个符合的字串，然后从那里出发，继续找
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
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        System.out.println(wordBreak2(s, wordDict));
//        System.out.println(s.substring(2, 8));
    }
}
