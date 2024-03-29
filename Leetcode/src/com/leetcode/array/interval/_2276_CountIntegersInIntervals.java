package com.leetcode.array.interval;

import java.util.Map;
import java.util.TreeMap;

public class _2276_CountIntegersInIntervals {
    TreeMap<Integer, Integer> map;
    int count;

    public _2276_CountIntegersInIntervals() {
        map = new TreeMap<>();  
        count = 0;
    }

    /*
     * Solution:
     * 首先去找比当前left小的最近的entry，如果没有或者当前这个
     * 的结束时候小于输入的开始说明不会有任何交集，所以我们要去
     * 找严格大于left的开始，然后依次循环会存在交集的情况，并且更新
     * count的值
     */
    public void add(int left, int right) {
        Map.Entry<Integer, Integer> it = map.floorEntry(left);
        if (it == null || it.getValue() < left) {
            it = map.higherEntry(left);
        }

        for (; it != null && it.getKey() <= right; it = map.higherEntry(left)) {
            left = Math.min(left, it.getKey());
            right = Math.max(right, it.getValue());
            count -= it.getValue() - it.getKey() + 1;
            map.remove(it.getKey());
        }
        map.put(left, right);
        count += right - left + 1;
    }

    public int count() {
        return count;
    }
}
