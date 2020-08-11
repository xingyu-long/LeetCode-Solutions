package com.leetcode.Design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _981_TimeBasedKeyValueStore {
    // 基于map<key, data>
    public class Data {
        int timestamp;
        String value;

        public Data(int t, String val) {
            timestamp = t;
            value = val;
        }
    }
    // hashmap. key, <timeStamp, value>
    // timestamp is increasing.
    /** Initialize your data structure here. */
    // 里面用pair。。。
    HashMap<String, List<Data>> map;

    public _981_TimeBasedKeyValueStore() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        if (!map.containsKey(key)) {
            map.put(key, new ArrayList<>());
        }
        map.get(key).add(new Data(timestamp, value));
    }

    // time:O(log(N))
    public String get(String key, int timestamp) {
        if (map.get(key) == null) return "";
        List<Data> list = map.get(key);
        return find(list, timestamp);
    }

    public String find(List<Data> list, int time) {
        int left = 0;
        int right = list.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).timestamp <= time) left = mid;
            else right = mid;
        }
        if (list.get(right).timestamp <= time) return list.get(right).value;
        else if (list.get(left).timestamp <= time) return list.get(left).value;
        return "";
    }
}
