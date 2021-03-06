/*
 * @Date: 04/07/2021 09:14:33
 * @LastEditTime: 04/07/2021 09:14:51
 * @Description: String
 */
package com.leetcode.string;

public class _1704_DetermineIfStringHalvesAreAlike {
    public boolean halvesAreAlike(String s) {
        int a = 0, b = 0;
        String vowels = "aeiouAEIOU";
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (vowels.indexOf(ch) != -1) {
                if (i < s.length() / 2)
                    a++;
                else
                    b++;
            }
        }
        return a == b;
    }
}
