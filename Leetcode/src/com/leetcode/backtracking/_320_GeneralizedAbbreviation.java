package com.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

public class _320_GeneralizedAbbreviation {

    /**
     * 320. Generalized Abbreviation
     * time:10/14/2019
     * review1:10/28/2019
     *
     * 记得画图出来理解整个过程
     * @param word
     * @return
     */
    public static List<String> generateAbbreviations(String word) {
        if (word == null || word.length() == 0) return new ArrayList<>();
        List<String> res = new ArrayList<>();
        dfs(res, word,0, "", 0);
        return res;
    }

    /**
     * pos: 指着当前的character
     * cur: 当前的字符串（已缩写）
     * count: 已经缩写的字母数
     */
    public static void dfs(List<String> res, String word, int pos, String cur, int count) {
        if (pos == word.length()) {
            if (count > 0) cur += count;
            res.add(cur);
            return;
        }
        // 当前进行缩写
        dfs(res, word, pos + 1, cur, count + 1);
        // 当前保持不变
        if (count > 0)
            cur += count;
        cur += word.charAt(pos);
        dfs(res, word, pos + 1, cur, 0);
    }

    public static void main(String[] args) {
        List<String> res = generateAbbreviations("abc");
        for (String str : res) {
            System.out.print(str + " ");
        }
    }
}
