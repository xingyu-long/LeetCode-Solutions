/*
 * @Date: 08/24/2019 12:37:05
 * @LastEditTime: 06/05/2022 17:30:47
 * @Description: Split, Trim
 */
package com.leetcode.string;

public class _58_LengthofLastWord {

    // time: O(n), space:O(1)
    public static int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(" ") - 1;

    }

    // time:O(1) space: O(n)
    public static int lengthOfLastWord2(String s) {
        String[] arr = s.split("\\s+");
        if (arr.length == 0) return 0;
        return arr[arr.length-1].length();
    }
}
