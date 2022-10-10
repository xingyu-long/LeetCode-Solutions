package com.leetcode.array.interval;

import java.util.TreeMap;

public class _731_MyCalendarII {
    TreeMap<Integer, Integer> map;

    public _731_MyCalendarII() {
        map = new TreeMap<>();
    }

    /*
     * Solution:
     * 将每组时间的起点和终点看成需要+1或者-1的地方
     * 每一次默认添加之后，然后遍历treemap遍历所有
     * 值，并且count的数目则是重叠会议的数目
     */
    public boolean book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        int count = 0;
        for (int key : map.keySet()) {
            count += map.get(key);
            if (count > 2) {
                // restore
                map.put(start, map.get(start) - 1);
                if (map.get(start) == 0) {
                    map.remove(start);
                }

                map.put(end, map.get(end) + 1);
                if (map.get(end) == 0) {
                    map.remove(end);
                }
                return false;
            }
        }
        return true;
    }
}
