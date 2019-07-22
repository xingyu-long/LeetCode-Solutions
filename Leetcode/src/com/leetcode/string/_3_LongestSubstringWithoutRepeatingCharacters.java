package com.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;

public class _3_LongestSubstringWithoutRepeatingCharacters {

    /**
     *  3. Longest Substring Without Repeating Characters
     *  When: 2019/04/11
     *  Review1: 2019/7/22
     *
     *
     * solution:
     * 1. 利用hashMap<Character, Integer> 存储其值，
     * 当遇到相同的字符的时候就把原来的value+1，移动在重复字符的位置后面一位
     * 从那个位置算起 然后有个 i - j + 1个长度
     *
     *
     * @param s
     * @return
     */

    // time:O(n) space:O(n)
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        // j来记录不重复的开始
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // 利用hash数组来做，并且记录其string
    // 这里使用128的数组是为了防止 " "输入无效，这样不用减'a'
    // 可以不计算 word 这样降低运行速度
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] count = new int[128];
        int begin = 0;
        int res = 0;
        String word = "";
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)]++;
            if (count[s.charAt(i)] == 1) {
                word += s.charAt(i);
                if (res < word.length()) {
                    res = word.length();
                }
            } else {
                //表示重复的时候 需要移动begin
                while (begin < i && count[s.charAt(i)] > 1) {
                    count[s.charAt(begin)]--; //是begin对应的字符数量减1
                    begin++;
                }
                word = "";
                for (int j = begin; j <= i; j++) {
                    word += s.charAt(j);
                }
            }
        }
        return res;
    }
}
