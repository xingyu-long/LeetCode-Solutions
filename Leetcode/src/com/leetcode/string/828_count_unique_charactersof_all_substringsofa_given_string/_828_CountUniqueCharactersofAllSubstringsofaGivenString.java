/*
 * @Date: 07/24/2022 12:02:38
 * @LastEditTime: 07/24/2022 12:03:43
 * @Description: String
 */
package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _828_CountUniqueCharactersofAllSubstringsofaGivenString {
     // 每个字符在对应的字符串里只允许出现一次
     // time: O(26 * n)
     // space: O(26 -> 1)
    public int uniqueLetterString(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int n = s.length();
        List<Integer>[] list = new List[26];
        for (int i = 0; i < 26; i++) {
            list[i] = new ArrayList<>();
            list[i].add(-1);
        }
        for (int i = 0; i < n; i++) {
            int pos = s.charAt(i) - 'A';
            list[pos].add(i);
        }
        for (int i = 0; i < 26; i++) {
            list[i].add(n);
        }
        // 遍历每一个字符的triple组合
        int res = 0;
        for (int i = 0; i < 26; i++) {
            for (int j = 1; j < list[i].size() - 1; j++) {
                res += (list[i].get(j) - list[i].get(j - 1)) * (list[i].get(j + 1) - list[i].get(j));
            }
        }
        return res;
    }
 }