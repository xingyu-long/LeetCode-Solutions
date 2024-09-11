package com.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 07/26/2020
 * @Description: String, Split.
 **/
public class _1525_NumberofGoodWaystoSplitaString {
    // 统计从左边开始到该位置的个数，以及从右边开始到该位置的个数
    public int numSplits(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        Map<Character, Integer> map = new HashMap<>();
        int n = s.length();
        int[] left = new int[n];
        int[] right = new int[n];
        // iterate from left;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            left[i] = map.size();
        }
        map.clear();
        for (int i = n - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
            right[i] = map.size();
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (left[i] == right[i + 1]) {
                res++;
            }
        }
        return res;
    }
}
