package com.leetcode;

import java.util.Arrays;
import java.util.HashMap;

public class _1048_LongestStringChain {
    // 一样的思路，只是先排序，然后利用HashMap来保持更新（删除当前的其中一个字符，然后与前面比较）。
    // time:O(|words| * len(word)) space:O(n)
    public int longestStrChain(String[] words) {
        if (words == null || words.length == 0) return 0;
        Arrays.sort(words, (a, b) -> (a.length() - b.length()));
        HashMap<String, Integer> map = new HashMap<>();
        int res = 0;
        for (String word : words) {
            if (map.containsKey(word)) continue;
            map.put(word, 1);
            for (int i = 0; i < word.length(); i++) { // word中随便删除一个看前面是否存在
                StringBuilder sb = new StringBuilder(word);
                String next = sb.deleteCharAt(i).toString();
                if (map.containsKey(next) && map.get(next) + 1 > map.get(word)) {
                    map.put(word, map.get(next) + 1);
                }
                res = Math.max(res, map.get(word));
            }
        }
        return res;
    }

    // 这个检查太耗费时间。
    public boolean isValid(String s1, String s2) {
        int res = 0;
        int[] count = new int[26];
        for (char ch : s2.toCharArray()) {
            count[ch - 'a']++;
        }
        for (char ch : s1.toCharArray()) {
            count[ch - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] > 1 || count[i] < -1) return false;
            if (count[i] == 1) res++;
        }
        return res == 1;
    }

    public static void main(String[] args) {
        _1048_LongestStringChain chain = new _1048_LongestStringChain();
        String[] words = {"a","b","ba","bca","bda","bdca"};
        chain.longestStrChain(words);
    }
}
