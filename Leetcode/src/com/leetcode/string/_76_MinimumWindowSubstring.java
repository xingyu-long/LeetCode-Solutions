package com.leetcode.string;

public class _76_MinimumWindowSubstring {

    /**
     *  76. Minimum Window Substring
     *  When: 2019/04/09
     *  Review1:2019/7/22
     *
     * solution：依然使用双指针的思想，主要是i 从前往后走
     * j 记录规定内的起始位置
     *  这个比较难 也很重要，先背代码
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        int[] count = new int[128];
        for (char c : t.toCharArray()) {
            count[c]++;
        }

        int from = 0;
        int total = t.length();
        int min = Integer.MAX_VALUE;// 用来记录最小的值（这个值是用来记录两者之间的距离）
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (count[s.charAt(i)]-- > 0) total--; //每个循环的都会进行--操作
            // total为0表示已经在s找到了包含t的字符串
            while (total == 0) {
                if (i - j + 1 < min) {
                    min = i - j + 1;
                    from = j;
                }
                // j用来记录目标的起始位置
                // 如果加加操作用到了 t字符里面包含的 会导致total++;
                if (++count[s.charAt(j++)] > 0) total++;
            }
        }
        return (min == Integer.MAX_VALUE) ? "" : s.substring(from, from + min);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }
}
