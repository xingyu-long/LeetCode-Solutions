/*
 * @Date: 08/11/2020 15:07:14
 * @LastEditTime: 08/15/2021 11:02:00
 * @Description: Sliding Window
 */
package com.leetcode.string.sliding_window;

public class _76_MinimumWindowSubstring {
    // time: O(m + n), space: O(1)
    // 更加清晰的个人解法，这里需要注意应该每个单词都会被--，只有++后>0才能使num++（才代表是目标字符）。
    public String minWindow2(String s, String t) {
        if (s == null || s.length() == 0 ||
                t == null || t.length() == 0) return "";
        int end = 0;
        int start = 0;
        int min = Integer.MAX_VALUE;
        String res = "";
        int[] counter = new int[128];
        for (char ch : t.toCharArray()) {
            counter[ch]++;
        }
        int num = t.length();
        while (end < s.length()) {
            if (counter[s.charAt(end)]-- > 0) num--;
            while (num == 0) {
                if (end - start + 1 < min) { // 防止a a这种test case
                    min = end - start + 1;
                    res = s.substring(start, end + 1);
                }
                counter[s.charAt(start)]++; // 不要写成counter[start]!!
                if (counter[s.charAt(start)] > 0) num++; // 这个边界也一定要分析清楚
                start++;
            }
            end++;
        }
        return res;
    }
}
