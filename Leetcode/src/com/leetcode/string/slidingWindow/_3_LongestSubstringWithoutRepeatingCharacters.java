package com.leetcode.string.slidingWindow;

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
                    count[s.charAt(begin)]--; //为了减去当前i重复的情况, 然后从非重复的地方开始走
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


    // 利用模板的做法，这里记录一个boolean数组
    public int lengthOfLongestSubstring4(String s) {
        if (s == null || s.length() == 0) return 0;
        boolean[] ch = new boolean[128];
        int start = 0;
        int max = 0;
        for (int end = 0; end < s.length(); end++) {
            while (ch[s.charAt(end)]) {
                ch[s.charAt(start)] = false;
                start++;
            }
            ch[s.charAt(end)] = true;
            max = Math.max(max, end - start + 1);
        }
        return max;
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
