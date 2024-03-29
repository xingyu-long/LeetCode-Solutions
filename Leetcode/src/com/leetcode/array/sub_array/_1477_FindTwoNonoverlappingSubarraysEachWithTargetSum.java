package com.leetcode.array.sub_array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 06/23/2020, 09/07/2020
 * @Description: subarray sum, sliding window
 **/
public class _1477_FindTwoNonoverlappingSubarraysEachWithTargetSum {
    // 利用prefix sum以及Map来做，
    public int minSumOfLengths(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int res = Integer.MAX_VALUE;
        int[] min = new int[n]; // 当目前位置构成target的最短长度。
        Arrays.fill(min, Integer.MAX_VALUE);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            // best so far
            if (i > 0) min[i] = Math.min(min[i], min[i - 1]);
            if (map.containsKey(sum - target)) {
                int prev = map.get(sum - target);
                min[i] = Math.min(min[i], i - prev);
                if (prev != -1 && min[prev] != Integer.MAX_VALUE) {
                    res = Math.min(res, i - prev + min[prev]);
                }
            }
            // 更新sum对应的坐标。
            map.put(sum, i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
