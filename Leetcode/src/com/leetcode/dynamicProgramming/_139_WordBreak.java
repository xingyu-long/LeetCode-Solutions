package com.leetcode.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;
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


    public static void main(String[] args) {
        String s = "catsandog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cats");
        wordDict.add("dog");
        wordDict.add("sand");
        wordDict.add("and");
        wordDict.add("cat");
        System.out.println(wordBreak(s, wordDict));
//        System.out.println(s.substring(2, 8));
    }
}
