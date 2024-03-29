/*
 * @Date: 12/12/2020 20:18:14
 * @LastEditTime: 12/12/2020 20:19:35
 * @Description: DP, String
 */
package com.leetcode.dynamic_programming;

public class _467_UniqueSubstringsInWraparoundString {
    public int findSubstringInWraproundString(String p) {
        // 没有什么思路。。
        // 1. 两个的字符的差别是1 或者26 + 1 -> 0这种 √
        // 2. 感觉可以试试暴力解法？TLE
        // 求以当前字符结尾的最长连续子串，这也代表可以构成多少个。
        // 例如abc: a, ab(b, ab), abc(c, bc, abc)刚好等于每种的长度
        int res = 0;
        int[] count = new int[26];
        int len = 0;
        for (int i = 0; i < p.length(); i++) {
            if (i > 0 && (p.charAt(i) == p.charAt(i - 1) + 1 || p.charAt(i - 1) - p.charAt(i) == 25)) {
                len++;
            } else {
                len = 1;
            }
            count[p.charAt(i) - 'a'] = Math.max(len, count[p.charAt(i) - 'a']);
        }
        for (int i = 0; i < 26; i++) {
            res += count[i];
        }
        return res;
    }
}
