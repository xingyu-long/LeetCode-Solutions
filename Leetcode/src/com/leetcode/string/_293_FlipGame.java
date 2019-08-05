package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _293_FlipGame {
    /**
     *  293. Flip Game
     *  When:2019/8/5
     *  Difficulty: Easy
     * @param s
     * @return
     */
    // time: O(n) space:O(n)
    public static List<String> generatePossibleNextMoves(String s) {
        List<String> res = new ArrayList<>();
        if (s == null && s.length() == 0) return res;
        StringBuilder sb = new StringBuilder(s);
        for(int i = 0; i < sb.length() - 1; i++) {
            if(sb.charAt(i) == sb.charAt(i + 1) && sb.charAt(i) == '+') {
                sb.setCharAt(i, '-');
                sb.setCharAt(i + 1, '-');
                res.add(sb.toString());
                //还原
                sb.setCharAt(i, '+');
                sb.setCharAt(i + 1, '+');
            }
        }
        return res;
    }

    // 不用改变当前的数组
    public static List<String> generatePossibleNextMoves2(String s) {
        List<String> res = new ArrayList<>();
        if (s == null && s.length() == 0) return res;
        for(int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '+' && s.charAt(i - 1) == '+') {
                res.add(s.substring(0, i - 1) + "--" + s.substring(i + 1));
            }
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(generatePossibleNextMoves2("++++"));
    }
}
