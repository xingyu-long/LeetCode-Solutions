package com.leetcode.string.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class _1297_MaximumNumberofOccurrencesofaSubstring {
    // 利用sliding window并且这里取字符串的时候需要注意
    // 取min_size的长度，是可以尽可能的匹配上相同的个数。
    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int start = 0, end = 0, res = 0, distinct = 0;
        int n = s.length();
        int[] count = new int[256];
        Map<String, Integer> map = new HashMap<>();
        while (end < n) {
            if (count[s.charAt(end)]++ == 0)
                distinct++;
            while (start < end && (distinct > maxLetters || end - start + 1 > maxSize)) {
                count[s.charAt(start)]--;
                if (count[s.charAt(start)] == 0)
                    distinct--;
                start++;
            }

            if (end - start + 1 < minSize) {
                end++;
                continue;
            }

            String temp = s.substring(end + 1 - minSize, end + 1);
            System.out.println(temp);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
            res = Math.max(res, map.get(temp));
            end++;
        }
        return res;
    }
}