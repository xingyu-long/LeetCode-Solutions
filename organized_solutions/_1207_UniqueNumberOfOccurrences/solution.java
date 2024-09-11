package com.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class _1207_UniqueNumberOfOccurrences {

    /**
     * 1207. Unique Number of Occurrences
     * When:2019/9/29-30
     *
     * @param arr
     * @return
     */
    // time:O(n) space:O(n)
    public boolean uniqueOccurrences(int[] arr) {
        // use hashmap
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        HashSet<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (!set.add(entry.getValue())) return false;
        }
        return true;
    }

    // 一样的思路，但是更加简洁的写法
    // 如果没有重复的count，那么map的大小和set里面装入的应该是相同的
    public boolean uniqueOccurrences2(int[] arr) {
        if (arr == null || arr.length == 0) return true;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : arr) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        HashSet<Integer> set = new HashSet<>();
        set.addAll(map.values());
        return map.size() == set.size();
    }
}
