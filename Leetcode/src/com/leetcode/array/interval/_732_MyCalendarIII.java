package com.leetcode.array.interval;

import java.util.TreeMap;

public class _732_MyCalendarIII {
    TreeMap<Integer, Integer> map;

    public _732_MyCalendarIII() {
        map = new TreeMap<>();    
    }

    /*
     * Solution:
     * 这里还是一个meeting room的问题，只需要关心
     * 起始点和结束点，然后计算到目前为止出现多个人
     * 在同一个时间点情况，即count的数目。
     */
    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);

        int res = 0;
        int count = 0;
        for (int v : map.values()) {
            count += v;
            res = Math.max(res, count);
        }
        return res;
    }
}
