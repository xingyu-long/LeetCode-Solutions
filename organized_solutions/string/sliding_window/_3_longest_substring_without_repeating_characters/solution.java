package com.leetcode.string.sliding_window;

import java.util.HashMap;
import java.util.HashSet;

public class _3_LongestSubstringWithoutRepeatingCharacters {

    /**
     * 3. Longest Substring Without Repeating Characters
     * When: 2019/04/11
     * Review1: 2019/7/22
     * review2: 11/3/2019
     * <p>
     * solution:
     * 1. 利用hashMap<Character, Integer> 存储其值，
     * 当遇到相同的字符的时候就把原来的value+1，移动在重复字符的位置后面一位
     * 从那个位置算起 然后有个 i - j + 1个长度
     *
     * @param s
     * @return
     */

    // 可以利用hashMap, set, int[] 来标记
    // time: O(n) space:O(n) 利用hashmap然后决定begin的位置
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int res = 0;
        // j来记录不重复的开始
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                j = Math.max(j, map.get(s.charAt(i)) + 1);
                // eg: ABCDDEC 这种情况走到最后一个C的时候 但是begin还是会在第二个d这个位置

            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - j + 1);
        }
        return res;
    }

    // 利用hashset
    public int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) return 0;
        HashSet<Character> set = new HashSet<>();
        int res = 0;
        int from = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                set.remove(s.charAt(from++));
                i--; // 利用这个保持快指针不动！！
            } else {
                set.add(s.charAt(i));
                res = Math.max(res, set.size()); //如果不在else语句中，这样也没有意义，因为依然有重复
            }
        }
        return res;
    }

    // 利用数组来做。比较经典！
    public int lengthOfLongestSubstring5(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] counter = new int[128];
        int start = 0;
        int end = 0;
        int res = 0;
        while (end < s.length()) {
            int pos = s.charAt(end);
            counter[pos]--;
            while (counter[pos] < -1) {
                counter[s.charAt(start)]++;
                start++;
            }
            end++;
            res = Math.max(res, end - start);
        }
        return res;
    }
}
