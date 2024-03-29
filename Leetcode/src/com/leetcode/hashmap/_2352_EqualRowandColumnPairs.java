/*
 * @Date: 07/24/2022 10:10:13
 * @LastEditTime: 08/08/2022 16:30:58
 * @Description: You need to fill out
 */
package com.leetcode.hashmap;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class _2352_EqualRowandColumnPairs {
    // time: O(m * n) space: O(n)
    /*
     * 解题思路:
     * 首先是序列化行或者列，然后找其对应的比较，但是这里并不是说比较成功就会一起移除，而是说这种
     * 匹配关系可以重复利用，所以对于这个map的用处只负责获取值即可
     */
    public int equalPairs(int[][] grid) {
        int res = 0;
        int m = grid.length, n = grid[0].length;
        Map<String, Integer> map = new HashMap<>();
        for (int j = 0; j < n; j++) {
            int[] arr = new int[m];
            for (int i = 0; i < m; i++) {
                arr[i] = grid[i][j];
            }
            String str = Arrays.toString(arr);
            map.put(str, map.getOrDefault(str, 0) + 1);
        }
        for (int i = 0; i < m; i++) {
            String str = Arrays.toString(grid[i]);
            if (map.containsKey(str)) {
                res += map.get(str);
            }
        }
        return res;
    }

}