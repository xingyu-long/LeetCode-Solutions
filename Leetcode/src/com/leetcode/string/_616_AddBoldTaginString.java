package com.leetcode.string;

/**
 * @Date: 05/31/2020
 * @Description: String
 **/
public class _616_AddBoldTaginString {

    /**
     * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b>
     * and </b> to wrap the substrings in s that exist in dict. If two such substrings overlap, you
     * need to wrap them together by only one pair of closed bold tag. Also, if two substrings
     * wrapped by bold tags are consecutive, you need to combine them.
     * Input: s = "abcxyz123" dict =
     * ["abc","123"] Output: "<b>abc</b>xyz<b>123</b>"
     * Input: s = "aaabbcc" dict =
     * ["aaa","aab","bc"] Output: "<b>aaabbc</b>c"
     * @param s
     * @param dict
     * @return
     */
    public String addBoldTag(String s, String[] dict) {
        // 其实不太懂如何联合起来？？？？ -> 下面这个解法确实很巧妙，去统计可以走的情况
        if (s == null || s.length() == 0) {
            return "";
        }
        int n = s.length();
        boolean[] bold = new boolean[n];
        for (int i = 0, end = 0; i < n; i++) {
            for (String word : dict) {
                if (s.startsWith(word, i)) {
                    end = Math.max(end, i + word.length());
                }
            }
            bold[i] = end > i;
            // System.out.println("i = " + i + " bold[" + i + "] = " + bold[i]);
        }

        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (index < n) {
            if (!bold[index]) {
                sb.append(s.charAt(index));
                index++;
                continue;
            }
            int j = index;
            while (j < n && bold[j]) {
                j++;
            }
            sb.append("<b>" + s.substring(index, j) + "</b>");
            index = j;
        }
        return sb.toString();
    }
}