package com.leetcode.string;

public class _387_FirstUniqueCharacterInAString {

    /**
     *  387. First Unique Character in a String
     *  When:2019/7/16
     *  review1:2019/8/24
     *
     *  Difficulty: Easy
     *  solution:
     *  用一个字符数组记录个数，然后看==1的那个i输出即可
     * @param s
     * @return
     */
    public static int firstUniqChar(String s) {
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s.length(); i++) {
            if (count[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }
}
