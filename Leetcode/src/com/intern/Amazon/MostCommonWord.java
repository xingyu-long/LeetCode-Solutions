package com.intern.Amazon;

import java.util.*;

public class MostCommonWord {
    /**
     * 819. Most Common Word
     * time: 10/21/2019
     * Difficulty: Easy
     * @param paragraph
     * @param banned
     * @return
     */
    // *** 注意test case 可能会有变形，例如大小写区分
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) return "";
        String[] words = paragraph.toLowerCase().split("[\\W]+"); // 表示分割掉非单词的部分
        HashSet<String> set = new HashSet();
        for (String ban : banned) {
            set.add(ban);
        }
        String res = "";
        int max = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (!set.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
                if (map.get(word) > max) {
                    max = map.get(word);
                    res = word;
                }
            }
        }
        return res;
    }
    // 只区分空格
    public static List<String> mostCommonWord2(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.length() == 0) return new ArrayList<>();
        String[] words = paragraph.toLowerCase().split("\\s+"); // 表示分割掉非单词的部分
        HashSet<String> set = new HashSet<>();
        for (String ban : banned) {
            set.add(ban);
        }
        List<String> res = new ArrayList<>();
        int max = 0;
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            if (!set.contains(word)) {
                map.put(word, map.getOrDefault(word, 0) + 1);
                if (map.get(word) > max) {
                    max = map.get(word);
                }
            }
        }
        // hasmap的键值对
        for (String word : map.keySet()) {
            if (map.get(word) == max) res.add(word);
        }
        return res;
    }


    public static void main(String[] args) {
        String paragraph = "jack and jill went to the market to buy bread and cheese cheese is jack favorite food";
        String[] banned = {"to"};
        List<String> res = mostCommonWord2(paragraph, banned);
        for (String s : res) {
            System.out.print(s + " ");
        }
    }
}
