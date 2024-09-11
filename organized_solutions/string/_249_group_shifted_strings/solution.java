/*
 * @Date: 08/11/2020 16:07:14
 * @LastEditTime: 06/06/2022 21:24:41
 * @Description: Encode, offset
 **/
package com.leetcode.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class _249_GroupShiftedStrings {

    // https://www.cnblogs.com/lightwindy/p/8727587.html
    // 相对距离
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null || strings.length == 0) {
            return new ArrayList<>();
        }
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            int offset = s.charAt(0) - 'a';
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                char shifted = (char) (ch - offset);
                if (shifted < 'a') {
                    shifted += 26;
                }
                sb.append(shifted);
            }
            String key = sb.toString();
            map.putIfAbsent(key, new ArrayList<>());
            map.get(key).add(s);
        }
        for (List<String> value : map.values()) {
            res.add(new ArrayList<>(value));
        }
        return res;
    }
}