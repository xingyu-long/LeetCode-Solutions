package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;

public class _3_LongestSubstringWithoutRepeatingCharacters {

    /**
     * 3. Longest Substring Without Repeating Characters
     * When: 2019/04/11
     *
     * solution:
     * 1. 利用hashMap<Character, Integer> 存储其值，当遇到相同的字符的时候就把原来的value+1
     * 从那个位置算起 然后有个 i - j + 1个长度
     *
     *
     * time: O(n)
     * space: O(n)
     *
     * @param s
     * @return
     */
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
}
