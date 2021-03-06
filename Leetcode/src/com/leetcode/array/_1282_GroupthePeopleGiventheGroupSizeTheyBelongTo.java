package com.leetcode.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 07/30/2020
 * @Description: HashMap
 **/
public class _1282_GroupthePeopleGiventheGroupSizeTheyBelongTo {
    // 相当于先遍历一遍，然后把相同size全部pop出来。
    // time:O(N)
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            map.putIfAbsent(size, new ArrayList<>());
            map.get(size).add(i);
        }

        List<List<Integer>> res = new ArrayList<>();
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            if (list == null || list.size() == 0) continue;
            int count = map.get(key).size() / key;
            for (int j = 0; j < count; j++) {
                List<Integer> temp = new ArrayList<>();
                for (int i = 0; i < key; i++) {
                    temp.add(list.remove(0));
                }
                res.add(temp);
            }
        }
        return res;
    }

    public List<List<Integer>> groupThePeople2(int[] groupSizes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < groupSizes.length; i++) {
            int size = groupSizes[i];
            map.computeIfAbsent(size, l -> new ArrayList<>()).add(i);
            // 相当于边处理边删除，一旦那个size对应上就可以删除。
            if (map.get(size).size() == size) {
                res.add(map.remove(size));
            }

        }
        return res;
    }
}
