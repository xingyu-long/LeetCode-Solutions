package com.leetcode.string;

import java.util.HashMap;
import java.util.Map;

/**
 * @Date: 11/04/2020
 * @Description: String
 **/
public class _1640_Check_Array_Formation_Through_Concatenation {
    public boolean canFormArray(int[] arr, int[][] pieces) {
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
