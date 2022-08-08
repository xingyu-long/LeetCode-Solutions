/*
 * @Date: 2020-03-20 22:13:15
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-20 22:15:30
 */
package com.leetcode.dynamic_programming;

import java.util.HashMap;
import java.util.Map;

public class _446_ArithmeticSlicesIISubsequence {
    public int numberOfArithmeticSlices(int[] A) {
        // 多一个循环？
        // 相当于当前加入这一个元素 则是 前面的组合数即可，因为加入这一个数不会改变你构成的情况[1, 2, 3] + [4] 前面[1,2,3]更新后的1->2。
        // res += 2就可以了
        int res = 0;
        int n = A.length;
        Map<Integer, Integer>[] map = new Map[n];
        for (int i = 0; i < A.length; i++) {
            map[i] = new HashMap<>(i);
            for (int j = 0; j < i; j++) {
                long diff = (long) A[i] - A[j];
                if (diff <= Integer.MIN_VALUE || diff >= Integer.MAX_VALUE) 
                    continue;
                int d = (int) diff;
                int c1 = map[i].getOrDefault(d, 0);
                int c2 = map[j].getOrDefault(d, 0);
                res += c2;
                map[i].put(d, c1 + c2 + 1);
            }
        }
        return res;
    }
}