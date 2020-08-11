package com.leetcode.string;

/**
 * @Date: 07/19/2020
 * @Description: String
 **/
public class _443_StringCompression {

    // time:O(n) space:O(1)
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int j = 0;
        for (int i = 0; i < chars.length; i++) {
            int count = 1;
            char ch = chars[i];
            while (i + 1 < chars.length && chars[i] == chars[i + 1]) {
                count++;
                i++;
            }
            chars[j++] = ch;
            if (count != 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[j++] = c;
                }
            }
        }
        return j;
    }
}
