/*
 * @Date: 01/27/2021 23:24:24
 * @LastEditTime: 01/27/2021 23:25:11
 * @Description: You need to fill out
 */
package com.leetcode.greedy;

public class _1737_ChangeMinimumCharactersToSatisfyOneOfThreeConditions {
    public int minCharacters(String a, String b) {
        // greedy很多时候思路不明显
        return Math.min(Math.min(op1(a, b), op1(b, a)), op2(a, b));
    }

    private int op1(String a, String b) {
        int res = Integer.MAX_VALUE;
        for (int i = 1; i < 26; i++) {
            int count = 0;
            for (char ch : a.toCharArray()) {
                // 找比这个当前字符大的个数，则a需要将这些变小
                if ((ch - 'a') >= i)
                    count++;
            }

            for (char ch : b.toCharArray()) {
                // 找比这个当前字符小的个数，则b需要将这些变大
                if ((ch - 'a') < i)
                    count++;
            }

            res = Math.min(res, count);
        }
        return res;
    }

    private int op2(String a, String b) {
        int[] count = new int[26];
        for (char ch : a.toCharArray()) {
            count[ch - 'a']++;
        }
        for (char ch : b.toCharArray()) {
            count[ch - 'a']++;
        }
        int maxCommon = 0;
        for (int i = 0; i < 26; i++) {
            maxCommon = Math.max(maxCommon, count[i]);
        }
        return a.length() + b.length() - maxCommon;
    }
}
