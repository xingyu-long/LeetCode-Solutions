package com.leetcode.string.slidingWindow;

public class _76_MinimumWindowSubstring {

    /**
     * 76. Minimum Window Substring
     * When: 2019/04/09
     * Review1:2019/7/22
     * review2: 2019/8/29
     * review3:11/3/2019
     * 依然感觉比较难
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
            while (num <= 0) {
                if (end - start + 1 < min) {
                    min = end - start + 1;
                    res = s.substring(start, end + 1);
                }
                counter[s.charAt(start)]++;
                if (counter[s.charAt(start)] > 0) num++;
                start++;
            }
            end++;
        }
        return res;
    }
    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s, t));
    }
}
