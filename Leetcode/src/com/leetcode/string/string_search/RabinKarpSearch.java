package com.leetcode.string.string_search;

public class RabinKarpSearch {
    private static final int base = 101;

    public static int patternSearch(char[] text, char[] pattern) {
        int m = pattern.length;
        int n = text.length;
        long patternHash = creatHash(pattern, m - 1);
        long textHash = creatHash(text, m - 1);

        for (int i = 0; i <= n - m; i++) {
            if (patternHash == textHash &&
                    checkEqual(text, i, i + m - 1, pattern, 0, m - 1)) {
                return i;
            }
            if (i < n - m)
                textHash = recalculateHash(text, i, i + m, textHash, m);
        }

        return -1;
    }

    public static boolean checkEqual(char[] text, int textStart, int textEnd, char[] pattern, int patternStart, int patternEnd) {
        if (textEnd - textStart != patternEnd - patternStart) {
            return false;
        }

        while (textStart <= textEnd && patternStart <= patternEnd) {
            if (text[textStart] != pattern[patternStart]) {
                return false;
            }
            textStart++;
            patternStart++;
        }
        return true;
    }

    public static long recalculateHash(char[] str, int oldIndex, int newIndex, long oldHash, int patternLen) {
        long newHash = oldHash - str[oldIndex];
        newHash /= base;
        newHash += str[newIndex] * Math.pow(base, patternLen - 1);
        return newHash;
    }

    public static long creatHash(char[] chars, int end) {
        long hash = 0;
        for (int i = 0; i <= end; i++) {
            hash += chars[i] * Math.pow(base, i);
        }
        return hash;
    }

    public static void main(String[] args) {
        char[] text = {'A', 'B', 'C', 'D', 'C', 'D'};
        char[] pattern = {'C', 'D'};
        System.out.println(patternSearch(text, pattern));
    }
}
