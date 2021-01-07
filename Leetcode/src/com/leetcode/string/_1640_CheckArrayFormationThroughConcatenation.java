/*
 * @Date: 11/04/2020 20:14:43
 * @LastEditTime: 01/01/2021 14:27:33
 * @Description: HashMap
 */
package com.leetcode.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _1640_CheckArrayFormationThroughConcatenation {

    public boolean canFormArray(int[] arr, int[][] pieces) {
        // 可以记录数组的第一个数，然后尝试遍历原来的那个arr，这样一旦碰到，就需要遍历完才行
        // time: O(len(p) * len(p[0]) + len(arr))
        if (arr == null || arr.length == 0) {
            return false;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] p : pieces) {
            map.put(p[0], new ArrayList<>());
            for (int num : p) {
                map.get(p[0]).add(num);
            }
        }

        int index = 0;
        while (index < arr.length) {
            if (!map.containsKey(arr[index])) {
                return false;
            } else {
                List<Integer> match = map.get(arr[index]);
                for (int i = 0; i < match.size() && index < arr.length; i++) {
                    if (match.get(i) != arr[index]) {
                        return false;
                    } else {
                        index++;
                    }
                }
            }
        }

        return true;
    }

    // 转换成字符串比较。
    public boolean canFormArray2(int[] arr, int[][] pieces) {
        Map<Integer, String> map = new HashMap<>();
        for (int[] p : pieces) {
            int key = p[0];
            StringBuilder sb = new StringBuilder();
            for (int num : p) {
                sb.append(num).append("#");
            }
            map.put(key, sb.toString());
        }

        StringBuilder sb = new StringBuilder();
        for (int num : arr) {
            sb.append(num).append("#");
        }
        StringBuilder res = new StringBuilder();
        for (int num : arr) {
            res.append(map.getOrDefault(num, ""));
        }
        return res.toString().equals(sb.toString());
    }
}
