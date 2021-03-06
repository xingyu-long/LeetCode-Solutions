/*
 * @Date: 03/01/2021 09:09:53
 * @LastEditTime: 03/01/2021 09:11:19
 * @Description: Array, HashSet
 */
package com.leetcode.array;

import java.util.HashSet;
import java.util.Set;

public class _575_DistributeCandies {
    public int distributeCandies(int[] candyType) {
        if (candyType == null || candyType.length == 0) {
            return 0;
        }
        int n = candyType.length;
        int maxEat = n / 2;
        Set<Integer> set = new HashSet<>();
        for (int c : candyType) {
            set.add(c);
        }
        int res = (maxEat < set.size() ? maxEat : set.size());
        return res;
    }
}
