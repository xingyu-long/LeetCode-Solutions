/*
 * @Date: 08/11/2020 16:07:14
 * @LastEditTime: 07/21/2022 23:02:57
 * @Description: String
 */
package com.leetcode.string;


public class _443_StringCompression {

    // time:O(n) space:O(1)
    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            return 0;
        }
        int res = 0;
        int index = 0, n = chars.length;
        while (index < n) {
            char ch = chars[index];
            int count = 0;
            while (index < n && chars[index] == ch) {
                index++;
                count++;
            }
            chars[res++] = ch;
            if (count != 1) {
                for (char c : String.valueOf(count).toCharArray()) {
                    chars[res++] = c;
                }
            }
        }
        return res;
    }
}
