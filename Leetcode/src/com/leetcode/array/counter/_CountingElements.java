package com.leetcode.array.counter;

import java.util.Arrays;
import java.util.HashMap;

public class _CountingElements {
    /**
     * Given an integer array arr, count element x such that x + 1 is also in arr.
     * <p>
     * If there're duplicates in arr, count them seperately.
     * <p>
     * Input: arr = [1,2,3]
     * Output: 2
     * Explanation: 1 and 2 are counted cause 2 and 3 are in arr.
     * <p>
     * Input: arr = [1,1,3,3,5,5,7,7]
     * Output: 0
     * Explanation: No numbers are counted, cause there's no 2, 4, 6, or 8 in arr.
     * <p>
     * Input: arr = [1,3,2,3,5,0]
     * Output: 3
     * Explanation: 0, 1 and 2 are counted cause 1, 2 and 3 are in arr.
     * <p>
     * Input: arr = [1,1,2,2]
     * Output: 2
     * Explanation: Two 1s are counted cause 2 is in arr.
     *
     * @param arr
     * @return
     */
    public int countElements(int[] arr) {
        // 利用hashMap
        if (arr == null || arr.length == 0) return 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        // 需要排序才行 2. 把出现的num - 1全部加上
        Arrays.sort(arr);
        int res = 0;
        for (int num : arr) {
            if (map.get(num - 1) != null) {
                res += map.get(num - 1);
                map.remove(num - 1);
            }
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        return res;
    }
}
