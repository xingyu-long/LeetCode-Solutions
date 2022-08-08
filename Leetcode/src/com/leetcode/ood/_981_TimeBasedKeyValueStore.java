package com.leetcode.ood;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Date: 06/22/2020
 * @Description: Design
 **/
public class _981_TimeBasedKeyValueStore {
    // key -> treeMap<>;
    /**
     * Initialize your data structure here.
     */
    Map<String, TreeMap<Integer, String>> map;

    public _981_TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.putIfAbsent(key, new TreeMap<>());
        map.get(key).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (!map.containsKey(key)) {
            return "";
        }
        TreeMap<Integer, String> tree = map.get(key);
        Integer floor = tree.floorKey(timestamp); // 这个跟排的顺序也有关系
        if (floor == null) {
            return ""; // 这个要记得check一下
        }
        return map.get(key).get(floor);
    }
}
