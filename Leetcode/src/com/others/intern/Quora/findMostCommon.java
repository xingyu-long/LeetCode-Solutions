package com.intern.Quora;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class findMostCommon {
    /**
     *
     * Find the most common elements in a list.
     *
     * Ex.
     *  Input: A = [2, 2, 3, 3, 5]
     * Output: [2, 3]
     * */
    // 记录max的值并且用map的key来循环
    public static List<Integer> findCommon(List<Integer> list) {
        if (list == null || list.size() == 0) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : list) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            max = Math.max(max, map.get(num));
        }

        for (int key : map.keySet()) {
            if (map.get(key) == max) res.add(key);
        }
        return res;
    }
}
