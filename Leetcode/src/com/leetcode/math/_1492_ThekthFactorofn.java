package com.leetcode.math;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 06/27/2020
 * @Description: Math, sqrt
 **/
public class _1492_ThekthFactorofn {

    // time:O(n) space:O(1)
    public int kthFactor(int n, int k) {
        if (k > n) {
            return -1;
        }
        for (int d = 1; d <= n; d++) {
            if (n % d == 0 && --k == 0) {
                return d;
            }
        }
        return -1;
    }

    // time:O(sqrt(n)), space:O(sqrt(n))
    // 这个相当于是cache一半，解法比较有意思。
    public int kthFactor2(int n, int k) {
        List<Integer> factorList = new ArrayList<>();
        for (int factor = 1; factor * factor <= n; factor++) {
            if (n % factor == 0) {
                if (factor * factor != n) {
                    factorList.add(factor);
                }
                if (--k == 0) {
                    return factor;
                }
            }
        }
        int size = factorList.size();
        return k > size ? -1 : n / factorList.get(size - k);
    }
}
