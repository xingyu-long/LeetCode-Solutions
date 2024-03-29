package com.leetcode.string;

import java.util.List;

import com.leetcode.string.string_search.KMP;

public class _28_ImplementstrStr {
    /**
     * 28. Implement strStr()
     * when: 2019/03/12
     * Review1: 2019/8/3
     * review2: 2019/8/24
     * <p>
     * 解题思路：使用子串比较即可就是从0+needle的长度然后遍历 用equals()
     * 这里的 i <= haystack.length() - needle.length() 是因为 走needle那么长的话 不能走完整个haystack 会溢出
     * 涉及到的数据结构或者方法： substring()
     *
     * @param haystack
     * @param needle
     * @return
     */
    // time: O(n^2) substring:O(n) space:O(1)
    public static int strStr(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()
                || haystack == null || haystack.length() == 0) {
            return -1;
        }
        // 利用substring来做吧
        int len = needle.length();
        for (int i = 0; i <= haystack.length() - len; i++) {
            if (haystack.substring(i, i + len).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

    // 相当于是先找到needle在haystack的起点，如果一直相同 最后就返回当前的i
    // time: O(m * n) space:O(1)
    public static int strStr2(String haystack, String needle) {
        if (needle == null || needle.length() == 0) {
            return 0;
        }
        if (needle.length() > haystack.length()
                || haystack == null || haystack.length() == 0) {
            return -1;
        }
        int m = haystack.length(), n = needle.length();
        for (int i = 0; i <= m - n; i++) {
            //一直循环到跟needle开头的起点
            int j = 0;
            for ( ; j < n; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break;
                }
            }
            if (j == n) return i;
        }
        return -1;
    }

    // KMP O(m + n)
    public int strStr3(String haystack, String needle) {
        if (needle == null || needle.length() == 0) return 0;
        KMP kmp = new KMP();
        int[] next = kmp.build(needle);
        List<Integer> res = kmp.match(haystack, needle);
        return res.size() != 0 ? res.get(0) : -1;
    }


    public static void main(String[] args) {
        System.out.println(strStr("abcd", "dc"));
        String a = "abcd";
        System.out.println(a.substring(0, 2));
    }
}
