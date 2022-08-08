package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

/**
 * @Date: 06/03/2020, 09/03/2020
 * @Description:
 **/
public class _849_MaximizeDistancetoClosestPerson {
    // time:O(nlogn) space:O(n)
    public int maxDistToClosest(int[] seats) {
        // 利用treeSet
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1)
                treeSet.add(i);
        }

        int res = 0;
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 0) {
                Integer ceil = treeSet.ceiling(i); // >= i 最小的
                Integer floor = treeSet.floor(i);  // <= i 最大的
                int localMin = Integer.MAX_VALUE;
                if (ceil != null) {
                    localMin = Math.min(localMin, ceil - i);
                }
                if (floor != null) {
                    localMin = Math.min(localMin, i - floor);
                }
                res = Math.max(res, localMin);
            }
        }
        return res;
    }

    public int maxDistToClosest2(int[] seats) {
        if (seats == null || seats.length == 0) {
            return 0;
        }
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < seats.length; i++) {
            if (seats[i] == 1) {
                nums.add(i);
            }
        }
        int res = 0, n = nums.size();
        // 看做中间的连续0
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                res = Math.max(res, nums.get(0));
            } else {
                res = Math.max(res, (nums.get(i) - nums.get(i - 1)) / 2);
            }
        }
        if (n > 0) res = Math.max(res, seats.length - 1 - nums.get(n - 1));//[1,0,0,0] 的情况
        return res;
    }

    // 跟上面逻辑一样，但是用last来记录其位置，并且每次遍历就开始计算
    public int maxDistToClosest3(int[] seats) {
        int res = 0, last = -1, n = seats.length;
        for (int i = 0; i < n; i++) {
            if (seats[i] == 1) {
                res = last < 0 ? i : Math.max(res, (i - last) / 2);
                last = i;
            }
        }
        res = Math.max(res, n - last - 1);
        return res;
    }
}
