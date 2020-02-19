package com.leetcode.string.Subsequence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _792_NumberofMatchingSubsequences {
    // time:O(mklogn)
    // n is the length of s,
    // m is the length of words,
    // k is the largest length of word.
    public int numMatchingSubseq(String S, String[] words) {
        HashMap<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (!map.containsKey(ch)) map.put(ch, new ArrayList<>());
            map.get(ch).add(i);
        }

        int res = 0;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int prev = -1;
            int j;
            for (j = 0; j < word.length(); j++) {
                List<Integer> nums = map.get(word.charAt(j));
                prev = find(nums, prev);
                if (prev == -1) break;
                prev++;
            }
            if (j == word.length()) res++;
        }
        return res;
    }

    public int find(List<Integer> nums, int target) {
        if (nums == null) return -1;
        int left = 0;
        int right = nums.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums.get(mid) >= target) right = mid;
            else left = mid;
        }
        if (nums.get(left) >= target) return nums.get(left);
        if (nums.get(right) >= target) return nums.get(right);
        return -1;
    }
}
