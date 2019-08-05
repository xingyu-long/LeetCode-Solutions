package com.leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class _249_GroupShiftedStrings {

    /**
     * 249. Group Shifted Strings
     * When:2019/8/5
     * Difficulty: Medium
     * Solution: 一开始理解错了题意
     * @param strings
     * @return
     */
    // https://www.cnblogs.com/lightwindy/p/8727587.html
    // 相对距离
    public static List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        if (strings == null || strings.length == 0) return res;
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            int offset = str.charAt(0) - 'a';
            String key = "";
            for (int i = 0; i < str.length(); i++) {
                char c = (char)(str.charAt(i) - offset);
                if (c < 'a') {
                    c += 26;
                }
                key += c;
            }
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<>();
                map.put(key, list);
            }
            map.get(key).add(str);
        }
        for(String key : map.keySet()) {
            List<String> list = map.get(key);
            Collections.sort(list);
            res.add(list);
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        System.out.println(groupStrings(strs));
    }
}