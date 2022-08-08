/*
 * @Date: 09/05/2021 11:57:29
 * @LastEditTime: 09/05/2021 12:15:11
 * @Description: Math
 */
package com.leetcode.math;

import java.util.Arrays;

// time: O(n^2)
public class _899_OrderlyQueue {
    public String orderlyQueue(String s, int k) {
        if (k > 1) {
            char[] chs = s.toCharArray();
            Arrays.sort(chs);
            return new String(chs);
        }
        String res = s;
        for (int i = 1; i < s.length(); i++) {
            String temp = s.substring(i) + s.substring(0, i);
            if (res.compareTo(temp) > 0) {
                res = temp;
            }
        }
        return res;
    }
}
