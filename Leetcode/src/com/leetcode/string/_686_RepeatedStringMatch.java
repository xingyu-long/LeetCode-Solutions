package com.leetcode.string;

public class _686_RepeatedStringMatch {

    // time:O(A * B) space:O(B)
    public int repeatedStringMatch(String A, String B) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }
        if (sb.toString().contains(B)) return count;
        if (sb.append(A).toString().contains(B)) return count + 1;
        return -1;
    }
}
