package com.leetcode.binary_search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Date: 07/08/2020
 * @Description: TreeSet
 **/
public class _1182_ShortestDistancetoTargetColor {

    // time:O(distinct(color) * log (idxes))
    public List<Integer> shortestDistanceColor(int[] colors, int[][] queries) {
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();
        // build the map to store the information color -> idxes.
        for (int i = 0; i < colors.length; i++) {
            map.putIfAbsent(colors[i], new TreeSet<>());
            map.get(colors[i]).add(i);
        }
        List<Integer> res = new ArrayList<>();
        for (int[] query : queries) {
            int index = query[0], color = query[1];
            TreeSet<Integer> treeset = map.get(color);
            if (treeset == null) {
                res.add(-1);
                continue;
            }
            Integer ceil = treeset.ceiling(index);
            Integer floor = treeset.floor(index);
            if (ceil == null && floor == null) {
                res.add(-1);
                continue;
            }
            int found = Integer.MAX_VALUE;
            if (ceil != null) {
                found = Math.min(found, ceil - index);
            }
            if (floor != null) {
                found = Math.min(found, index - floor);
            }
            res.add(found);
        }
        return res;
    }
}
