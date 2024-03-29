package com.leetcode.array.interval;

import java.util.TreeMap;

public class _715_RangeModule {
    class RangeModule {

        TreeMap<Integer, Integer> map;

        public RangeModule() {
            map = new TreeMap<>();
        }

        /*
         * Solution:
         * 利用treemap来存储数据这样的话interval默认就是有序的
         * 然后利用floorKey找到可能的开始点，从那个点开始遍历
         * 针对于addRange，则需要考虑合并区间，所以只需要更新
         * left和right，最后，更新到map里面去。
         * 对于removeRange，则需要考虑被分割出来的独立区间
         * 需要将其加入到map中例如[start, left], [right, end]
         */
        // time: O(nlogn)
        public void addRange(int left, int right) {
            if (left >= right)
                return;
            Integer start = map.floorKey(left);
            if (start == null) {
                start = map.ceilingKey(left);
            }
            while (start != null && start <= right) {
                int end = map.get(start);
                if (left <= end) {
                    map.remove(start);
                    if (start < left) {
                        left = start;
                    }
                    if (end > right) {
                        right = end;
                    }
                }
                start = map.ceilingKey(end);
            }
            map.put(left, right);
        }

        public boolean queryRange(int left, int right) {
            Integer floor = map.floorKey(left);
            return (floor != null) && (map.get(floor) >= right);
        }

        // time: O(nlogn)
        public void removeRange(int left, int right) {
            if (left >= right)
                return;
            Integer start = map.floorKey(left);
            if (start == null) {
                start = map.ceilingKey(left);
            }
            while (start != null && start <= right) {
                int end = map.get(start);
                if (left <= end) {
                    map.remove(start);
                    if (start < left) {
                        map.put(start, left);
                    }
                    if (end > right) {
                        map.put(right, end);
                    }
                }
                start = map.ceilingKey(end);
            }
        }
    }
}
