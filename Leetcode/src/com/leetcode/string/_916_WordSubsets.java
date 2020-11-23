/*
 * @Date: 11/23/2020 09:50:24
 * @LastEditTime: 11/23/2020 09:51:10
 * @Description: String, count Array
 */
package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _916_WordSubsets {
    public List<String> wordSubsets(String[] A, String[] B) {
        int[] maxCount = new int[26];
        for (String s : B) {
            int[] count = countStr(s);
            for (int i = 0; i < 26; i++) {
                maxCount[i] = Math.max(maxCount[i], count[i]);
            }
        }

        List<String> res = new ArrayList<>();
        for (String s : A) {
            int[] count = countStr(s);
            int i = 0;
            for (; i < 26; i++) {
                if (count[i] < maxCount[i])
                    break;
            }
            if (i == 26) {
                res.add(s);
            }
        }
        return res;
    }

    public int[] countStr(String s) {
        int[] count = new int[26];
        for (char ch : s.toCharArray()) {
            count[ch - 'a']++;
        }
        return count;
    }
}
