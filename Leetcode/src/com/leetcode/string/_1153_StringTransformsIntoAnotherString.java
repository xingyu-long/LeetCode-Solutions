package com.leetcode.string;

import java.util.HashMap;
import java.util.HashSet;

public class _1153_StringTransformsIntoAnotherString {
    // 1153. String Transforms Into Another String
    // 这里是可以多对1. 所以原来的字符的字符组成应该是 <= 转换后字符串的字符组成
    public boolean canConvert(String str1, String str2) {
        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> s2 = new HashSet<>();
        for (int i = 0; i < str1.length(); i++) {
            if (map.containsKey(str1.charAt(i)) &&
                    map.get(str1.charAt(i)) != str2.charAt(i))
                return false;
            map.put(str1.charAt(i), str2.charAt(i));
            s2.add(str2.charAt(i));
        }

        if (map.size() < s2.size()) return false;
        else if (map.size() > s2.size()) return true;
        else {
            if (s2.size() == 26) return str1.equals(str2);
            else return true;
        }
    }

}
