package com.leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * @Date: 2019/8/5, 05/25/2020
 * @Description: Encode, offset
 **/
public class _249_GroupShiftedStrings {

    // https://www.cnblogs.com/lightwindy/p/8727587.html
    // 相对距离
    public static List<List<String>> groupStrings(String[] strings) {
        List<List<String>> res = new ArrayList<>();
        if (strings == null || strings.length == 0) {
            return res;
        }
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            int offset = str.charAt(0) - 'a';
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < str.length(); i++) {
                char c = (char) (str.charAt(i) - offset);
                if (c < 'a') {
                    c += 26;
                }
                sb.append(c);
            }
            String key = sb.toString();
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<>();
                map.put(key, list);
            }
            map.get(key).add(str);
        }
        for (String key : map.keySet()) {
            res.add(map.get(key));
        }
        return res;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};

        System.out.println(groupStrings(strs));
    }
}