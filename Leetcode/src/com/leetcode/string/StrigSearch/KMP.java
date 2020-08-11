package com.leetcode.string.StrigSearch;

import java.util.ArrayList;
import java.util.List;

public class KMP {

    /*

    idx 0 1 2 3 4 5 6 7
        A B C D A B D $
        0 0 0 0 0 1 2 0
          A B C D A B D $
    */
//    https://www.youtube.com/watch?v=uKr9qIZMtzw
    public int[] build(String p) {
        int m = p.length();
        int[] next = new int[m + 1];
        int idx = 2;
        for (int i = 1, j = 0; i < m; i++) { // 这里的i从1开始因为是同样的字符串前后比较
            while (j > 0 && p.charAt(j) != p.charAt(i)) {
                j = next[j];
            }
            if (p.charAt(i) == p.charAt(j)) {
                j++;
            }
            next[idx++] = j;
        }
        return next;
    }

    public List<Integer> match(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int[] next = build(p);
        int n = s.length();
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && s.charAt(i) != p.charAt(j)) {
                j = next[j];
            }
            if (s.charAt(i) == p.charAt(j)) {
                j++;
            }
            if (j == p.length()) { // 已经找到match的存在
                res.add(i - p.length() + 1);
                j = next[j];
            }
        }
        return res;
    }
}
