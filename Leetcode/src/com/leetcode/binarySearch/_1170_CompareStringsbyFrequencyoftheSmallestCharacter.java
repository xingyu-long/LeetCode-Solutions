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
        
        int[] sorted = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            sorted[i] = count(words[i]);
        }
        Arrays.sort(sorted);
        int[] res = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int pos = find(sorted, count(queries[i]));
            if (pos == -1) res[i] = 0;
            else res[i] = sorted.length - pos;
        }
        return res;
    }
    
    public int find(int[] sorted, int target) {
        int left = 0, right = sorted.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (sorted[mid] > target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (sorted[left] > target) return left;
        else if (sorted[right] > target) return right;
        return -1;
    }
    
    public int count(String str) {
        int[] arr = new int[26];
        for (char ch : str.toCharArray()) {
            arr[ch - 'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (arr[i] != 0) return arr[i];
        }
        return 0;
    }

}
