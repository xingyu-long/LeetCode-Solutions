/*
 * @Date: 02/07/2021 09:41:57
 * @LastEditTime: 02/07/2021 09:47:29
 * @Description: DP, simulation
 */
package com.leetcode.string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class _821_ShortestDistanceToACharacter {
    // brute force: 记录位置，然后向左向右移动
    // time: O(n)
    public int[] shortestToChar(String s, char c) {
        if (s == null || s.length() == 0) {
            return new int[] {};
        }
        int n = s.length();
        int[] res = new int[n];
        Arrays.fill(res, Integer.MAX_VALUE);
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == c) {
                res[i] = 0;
                set.add(i);
            }
        }

        for (int i : set) {
            // 向左
            for (int j = i - 1, k = 1; j >= 0 && res[j] > k && res[j] != 0; j--, k++) {
                res[j] = k;
            }
            // 向右
            for (int j = i + 1, k = 1; j < n && res[j] > k && res[j] != 0; j++, k++) {
                res[j] = k;
            }
        }
        return res;
    }

    // DP，就是走前后各一次。
    public int[] shortestToChar2(String s, char c) {
        int n = s.length();
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = (s.charAt(i) == c ? 0 : n);
        }
        
        for (int i = 1; i < n; i++) {
            res[i] = Math.min(res[i], res[i - 1] + 1);
        }
        
        for (int i = n - 2; i >=0 ; i--) {
            res[i] = Math.min(res[i], res[i + 1] + 1);
        }
        
        return res;
    }
}
