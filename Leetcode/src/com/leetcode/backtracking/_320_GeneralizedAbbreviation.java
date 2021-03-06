package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _320_GeneralizedAbbreviation {
    public static void main(String[] args) {
        String str = "abc";
        List<String> res = generateAbbreviations2(str);
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }
    }

    public static List<String> generateAbbreviations2(String word) {
        if (word == null || word.length() == 0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        helper(res, "", word, 0, 0);
        return res;
    }

    // 画图理解就可以了。
    public static void helper(List<String> res, String cur, String word, int count, int start) {
        if (start == word.length()) {
            // 最后不为空就需要加入count。
            if (count != 0) cur += count;
            res.add(cur);
            return;
        }        
        // 不加入当前字符，所以只计数
        helper(res, cur, word, count + 1, start + 1);
        if (count != 0) {
            // 如果不为空就加入，并且将count置为0.
            cur += count;
            count = 0; // 下一步的时候可能还有不选择的时候选择计数，所以不能重复。
        }
        // 加入当前字符，不计数
        helper(res, cur + word.charAt(start), word, count, start + 1);
    }
}
