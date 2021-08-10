/*
 * @Date: 01/25/2020 16:27:58
 * @LastEditTime: 08/08/2021 11:21:06
 * @Description: Sort
 */
package com.leetcode.array.sort;

import java.util.Arrays;
import java.util.HashMap;

public class _1331_RankTransformofanArray {
    // time: O(nlogn) space:O(n)
    public int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0) return new int[]{};
        int[] sort = arr.clone();
        Arrays.sort(sort);
        HashMap<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < sort.length; i++) {
            if (!map.containsKey(sort[i])) {
                map.put(sort[i], rank++);
            }
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = map.get(arr[i]);
        }
        return res;
    }
}
