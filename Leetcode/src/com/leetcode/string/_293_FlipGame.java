package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _293_FlipGame {

    // time: O(n * substring()) space:O(n)
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
