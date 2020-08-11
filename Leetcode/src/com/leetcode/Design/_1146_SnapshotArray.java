package com.leetcode.Design;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class _1146_SnapshotArray {
    // When: 1/14/2020, 3/9/2020

    /**
    // TLE, 因为记录的地方不高效。
    class Data {
        int index;
        int val;
        public Data(int i, int v) {
            index = i;
            val = v;
        }
    }
    int[] arr;
    int snapId;
    HashMap<Integer, List<Data>> map;

    public _1146_SnapshotArray(int length) {
        arr = new int[length];
        snapId = 0;
        map = new HashMap<>();
    }

    public void set(int index, int val) {
        arr[index] = val;
    }

    // 每次操作tle。
    public int snap() {
        snapId++;
        List<Data> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0)
                list.add(new Data(i, arr[i]));
        }
        map.put(snapId - 1, list);
        return snapId - 1;
    }

    // 可以优化到logn
    public int get(int index, int snap_id) {
        List<Data> list = map.get(snap_id);
        return find(list, index);
    }

    public int find(List<Data> list, int target) {
        if (list.size() == 0) return 0;
        int left = 0;
        int right = list.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid).index >= target) right = mid;
            else left = mid;
        }
        if (list.get(left).index == target) return list.get(left).val;
        if (list.get(right).index == target) return list.get(right).val;
        return  0;
    }
    **/

    // 利用TreeMap，专门记录每次改变的情况。
    List<TreeMap<Integer, Integer>> arr;
    int snapId;

    // O(n)
    public _1146_SnapshotArray(int length) {
        arr = new ArrayList<>();
        snapId = 0;
        for (int i = 0; i < length; i++) {
            arr.add(i, new TreeMap<>());
            arr.get(i).put(0, 0);
        }
    }

    public void set(int index, int val) {
        arr.get(index).put(snapId, val);
    }

    public int snap() {
        return snapId++;
    }

    // log(m) index这个点有m次改变
    public int get(int index, int snap_id) {
        return arr.get(index).floorEntry(snap_id).getValue();
    }
}
