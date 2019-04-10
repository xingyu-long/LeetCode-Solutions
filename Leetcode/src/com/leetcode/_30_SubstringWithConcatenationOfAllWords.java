package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _30_SubstringWithConcatenationOfAllWords {

    /**
     * 30. Substring with Concatenation of All Words
     * when: 2019/04/10
     *
     * solution： 题目意思就是利用words里面的搭配知道在s中存在的位置
     * 但是这里有报错
     *
     * @param s
     * @param words
     * @return
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        if (s == null || words == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        int n = words.length; // 记录有多少个单词
        int m = words[0].length(); // 记录单个单词的长度（题目要求其每个单词的长度一致）
        HashMap<String, Integer> map = new HashMap<>();

        // 记录每个单词的出现情况
        for (String str: words) {
            // 这里的getOrDefault 有些问题，JDK原因？？？
            map.put(str, map.getOrDefault(str, 0) + 1);
        }

        for (int i = 0; i < s.length() - n * m; i++) {
            HashMap<String, Integer> copy = new HashMap<>(map);
            int k = n;
            int j = i; //记录行走的位置
            while (k > 0) {
                String str = s.substring(j, j + m);
                if (!copy.containsKey(str) || copy.get(str) < 1) {
                    break;
                }
                copy.put(str, copy.get(str) - 1);
                k--;
                j += m;
            }
            if (k == 0) res.add(i);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "wordgoodgoodgoodbestword";
        String[] words = new String[]{"word", "good", "best", "good"};
        System.out.println(findSubstring(s, words));
    }
}
