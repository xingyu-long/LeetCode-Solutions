package com.leetcode.array.interval;

import java.util.TreeMap;

public class _352_DataStreamasDisjointIntervals {
    TreeMap<Integer, int[]> map;
    /** Initialize your data structure here. */
    public _352_DataStreamasDisjointIntervals() {
        map = new TreeMap<>();
    }

    public void addNum(int val) {
        if (map.containsKey(val)) return;
        Integer lowerKey = map.lowerKey(val);
        Integer higherKey = map.higherKey(val);
        
        if (lowerKey != null && higherKey != null && val == map.get(lowerKey)[1] + 1 && val == map.get(higherKey)[0] - 1) {
             // 1. 刚好在中间
            map.get(lowerKey)[1] = map.get(higherKey)[1];
            map.remove(higherKey);
        } else if (lowerKey != null && val <= map.get(lowerKey)[1] + 1) {
            // 2. 靠左边近,只有左边会出现可能在区间内的情况，右边没办法出现（因为那样lowerKey肯定就是左边了）
            // [1, 4], value = 3 or 5
            map.get(lowerKey)[1] = Math.max(val, map.get(lowerKey)[1]);
        } else if (higherKey != null && val == map.get(higherKey)[0] - 1) {
            // 3. 靠右边近，并且会需要更新当前的treemap，因为key变了
            map.put(val, new int[]{val, map.get(higherKey)[1]});
            map.remove(higherKey);
        } else {
            // 4. 加入孤立的点（没有任何交叉）
            map.put(val, new int[]{val, val});
        }
    }

    public int[][] getIntervals() {
        int[][] res = new int[map.size()][2];
        int i = 0;
        for (int[] ret : map.values()) {
            res[i++] = ret;
        }
        return res;
    }
}
