/*
 * @Date: 01/28/2021 10:24:10
 * @LastEditTime: 01/29/2021 09:40:03
 * @Description: Greedy
 */
package com.leetcode.greedy;

import java.util.Arrays;

public class _1663_SmallestStringWithAGivenNumericValue {
    public String getSmallestString(int n, int k) {
        char[] res = new char[n];
        Arrays.fill(res, 'a');
        k -= n;

        int end = n - 1;
        while (k > 0) {
            int temp = Math.min(k, 25);
            res[end] += temp;
            k -= temp;
            end--;
        }
        return new String(res);
    }
}
