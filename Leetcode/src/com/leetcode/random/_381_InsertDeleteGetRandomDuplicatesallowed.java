package com.leetcode.random;

import java.util.*;

public class _381_InsertDeleteGetRandomDuplicatesallowed {
    // 操作原理和之前的380. 一样，只是需要注意的是map存的value是set
    HashMap<Integer, Set<Integer>> map;
    List<Integer> list;
    Random rmd;
    /** Initialize your data structure here. */
    public _381_InsertDeleteGetRandomDuplicatesallowed() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rmd = new Random();
    }

    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean contains = map.containsKey(val);
        if (!contains) {
            map.put(val, new HashSet<>());
        }
        map.get(val).add(list.size());
        list.add(val);
        return !contains;
    }

    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val).iterator().next(); // 这里是set的第0个值。
        map.get(val).remove(index);
        if (map.get(val).size() == 0) { // 如果删除后map对应的set为空就直接清除map
            map.remove(val);
        }
        int lastVal = list.remove(list.size() - 1);
        if (index != list.size()) {// 这里需要好好反思
            list.set(index, lastVal);
            map.get(lastVal).remove(list.size());
            map.get(lastVal).add(index);
        }
        return true;
    }

    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(rmd.nextInt(list.size()));
    }
}
