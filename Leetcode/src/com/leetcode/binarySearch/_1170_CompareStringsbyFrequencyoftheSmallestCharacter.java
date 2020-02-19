package com.leetcode.binarySearch;

import java.util.Arrays;

public class _1170_CompareStringsbyFrequencyoftheSmallestCharacter {
    // time: O(n * m) space:O(max(n,m))
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int[] queryCount = new int[queries.length];
        int[] wordsCount = new int[words.length];
        for (int i = 0; i < queries.length; i++) {
            queryCount[i] = countSmall(queries[i]);
        }
        for (int i = 0; i < words.length; i++) {
            wordsCount[i] = countSmall(words[i]);
        }
        int[] res = new int[queries.length];
        for (int i = 0; i < queryCount.length; i++) {
            int count = 0;
            for (int j = 0; j < wordsCount.length; j++) {
                if (queryCount[i] < wordsCount[j]) count++;
            }
            res[i] = count;
        }
        return res;
    }

    public int countSmall(String s) {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (count[i] != 0) return count[i];
        }
        return 0;
    }

    // time:O(m * logN) space:O(n)
    public int[] numSmallerByFrequency2(String[] queries, String[] words) {
        int[] queryCount = new int[queries.length];
        int[] wordsCount = new int[words.length];
        for (int i = 0; i < queries.length; i++) {
            queryCount[i] = countSmall(queries[i]);
        }
        for (int i = 0; i < words.length; i++) {
            wordsCount[i] = countSmall(words[i]);
        }
        int[] res = new int[queries.length];
        Arrays.sort(wordsCount);
        // use binary search
        for (int i = 0; i < queryCount.length; i++) {
            int left = 0;
            int right = wordsCount.length - 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2;
                if (wordsCount[mid] > queryCount[i]) right = mid;
                else left = mid;
            }
            if (wordsCount[left] > queryCount[i]) res[i] = wordsCount.length - left;
            else if (wordsCount[right] > queryCount[i]) res[i] = wordsCount.length - right;
            else res[i] = 0;
        }
        return res;
    }

}
