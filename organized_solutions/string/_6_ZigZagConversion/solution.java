package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _6_ZigZagConversion {
    public String convert(String s, int numRows) {
        int count = 2 * (numRows - 1);
        if (count == 0) return s;
        List<Character>[] list = new List[numRows];
        for (int i = 0; i < numRows; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < s.length(); i++) {
            int index = i % count;
            if (index >= numRows) {
                index = count - index;
            }
            list[index].add(s.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            for (char ch : list[i]) {
                sb.append(ch);
            }
        }
        return sb.toString();
    }
}
