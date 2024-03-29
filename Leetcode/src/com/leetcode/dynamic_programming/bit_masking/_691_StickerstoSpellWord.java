package com.leetcode.dynamic_programming.bit_masking;

import java.util.Arrays;

/**
 * @Date: 04/20/2020
 * @Description: bit masking DP
 **/
public class _691_StickerstoSpellWord {
    // 看成target是反过来的，因为后面K更新的原因。
    public int minStickers(String[] stickers, String target) {
        int n = target.length();
        int m = 1 << n;
        int[] dp = new int[m];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 0; i < m; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;
            for (String str : stickers) {
                // 尝试每一个单词里的所有字符看是否可以够着到target里面去
                int j = findNextStatus(i, target, str);
                dp[j] = Math.min(dp[j], dp[i] + 1);
                System.out.print("i = " + i + " j = " + j);
            }
        }
        return dp[m - 1] == Integer.MAX_VALUE ? -1 : dp[m - 1];
    }

    public int findNextStatus(int status, String target, String str) {
        int n = target.length();
        for (char ch : str.toCharArray()) {
            for (int k = 0; k < n; k++) {
                if (target.charAt(k) == ch && ((status >> k) & 1) == 0) {
                    status |= 1 << k;
                    break;
                }
            }
        }
        return status;
    }

    public static void main(String[] args) {
        String target = "abc";
        String[] strs = {"a", "b", "c"};
        _691_StickerstoSpellWord spellWord = new _691_StickerstoSpellWord();
        System.out.print(spellWord.minStickers(strs, target));

    }
}
