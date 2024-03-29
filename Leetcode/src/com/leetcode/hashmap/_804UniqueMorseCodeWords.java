/*
 * @Date: 11/22/2020 21:10:44
 * @LastEditTime: 08/08/2022 16:30:26
 * @Description: Map, Set
 */
package com.leetcode.hashmap;

import java.util.HashSet;
import java.util.Set;

public class _804UniqueMorseCodeWords {
    public int uniqueMorseRepresentations(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        String[] letterToCode = new String[] { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---",
                "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-",
                "-.--", "--.." };
        Set<String> set = new HashSet<>();
        for (String w : words) {
            StringBuilder sb = new StringBuilder();
            for (char ch : w.toCharArray()) {
                sb.append(letterToCode[ch - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();
    }
}
