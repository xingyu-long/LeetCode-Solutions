/*
 * @Date: 03/23/2021 10:44:03
 * @LastEditTime: 03/23/2021 10:53:22
 * @Description: 3 Sum follow up
 */
package com.leetcode.HashMap;

public class _923_3SumWithMultiplicity {
    public int threeSumMulti(int[] arr, int target) {
        long[] count = new long[101];
        for (int a : arr) {
            count[a]++;
        }
        long res = 0;
        for (int i = 0; i <= 100; i++) {
            for (int j = i; j <= 100; j++) {
                int k = target - i - j;
                if (k > 100 || k < 0)
                    continue;
                if (i == j && j == k) {
                    // C_{x}^3
                    res += count[i] * (count[i] - 1) * (count[i] - 2) / 6;
                } else if (i == j && j != k) {
                    // C_{x}^2 * C_{y}
                    res += count[i] * (count[i] - 1) / 2 * count[k];
                } else if (j < k) {
                    res += count[i] * count[j] * count[k];
                }
            }
        }
        return (int) (res % (1e9 + 7));
    }
}
