package com.leetcode.random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class _380_InsertDeleteGetRandom {

    HashMap<Integer, Integer> map;
    ArrayList<Integer> list;
    Random rmd;

    // time: O(1) space:O(n)
    /**
     *  380. Insert Delete GetRandom O(1)
     * When: 2019/06/27
     * review1: 11/9/2019

     solution: 主要使用ArrayList 以及 HashMap（分别保存值以及对应在list的坐标）

     test case: 主要关于在remove操作
     Before:
     HashMap:    val  i
     3    0
     2    1
     1    *2*
     list: [3,*2*,1]

     remove (val = 2)
     首先是 HashMap 中key = 2 被删除，但是这里的key = 1对应的val即i需要更新为1才对
     list 直接弹出最后一个 就剩下[3,2]
     所以这里需要更新map的i 以及 更新list的值
     list.set(1, 1)
     list = [3,1]
     map.put(1,1) 修改成功

     */
    /**
     * Initialize your data structure here.
     */
    public _380_InsertDeleteGetRandom() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rmd = new Random();
    }

    /**
     * Inserts a value to the set. Returns true if the set did not already contain the specified element.
     */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    /**
     * Removes a value from the set. Returns true if the set contained the specified element.
     */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;

        int index = map.remove(val); // will return the value of that key
        int lastVal = list.remove(list.size() - 1);
        //这一步很重要，关键如何理解(如何保持list和map同步)
        if (index != list.size()) { // 表示list删除的元素，在map中对应的位置不是最后一个。
            list.set(index, lastVal);
            map.put(lastVal, index);
        }
        return true;
    }

    /**
     * Get a random element from the set.
     */
    public int getRandom() {
        return list.get(rmd.nextInt(list.size()));
    }
}
