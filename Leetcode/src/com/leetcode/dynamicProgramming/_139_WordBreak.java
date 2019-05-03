package com.leetcode.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class _139_WordBreak {

    /**
     * 139. Word Break
     * When: 2019/05/03
     *
     * solution:
     * 目前不是特别了解，尽管说是用动态规划，需要跑一下test case
     *
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
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        System.out.println(wordBreak(s, wordDict));
    }
}
