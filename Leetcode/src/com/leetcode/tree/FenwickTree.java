/*
 * @Date: 04/11/2020 18:35:36
 * @LastEditTime: 01/10/2021 10:02:11
 * @Description: Fenwick Tree, Range Sum
 */
package com.leetcode.tree;

import java.util.Arrays;

public class FenwickTree {

    public int[] sums;

    public FenwickTree(int n) {
        sums = new int[n + 1];
        Arrays.fill(sums, 0);
    }

    private int lowBit(int x) {
        return x & (-x);
    }

    // delta表示是差值
    public void update(int i, int delta) {
        while (i < sums.length) {
            sums[i] += delta;
            i += lowBit(i);
        }
    }

    public int query(int i) {
        int sum = 0;
        while (i > 0) {
            sum += sums[i];
            i -= lowBit(i);
        }
        return sum;
    }
}
