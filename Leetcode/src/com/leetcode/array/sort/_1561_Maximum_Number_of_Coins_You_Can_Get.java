/*
 * @Date: 11/17/2020 16:05:08
 * @LastEditTime: 11/17/2020 16:25:05
 * @Description: Sort,
 */

package com.leetcode.array.sort;

import java.util.Arrays;

public class _1561_Maximum_Number_of_Coins_You_Can_Get {
    // 一开始以为是分组DP，然后有以为用PQ来获取
    // 后面才发现应该是sort 然后每次就看最大的两个。
    public int maxCoins(int[] piles) {
        if (piles == null || piles.length == 0) {
            return 0;
        }
        Arrays.sort(piles);
        int n = piles.length;
        int res = 0;
        // eg: S S M L M L
        for (int i = n / 3; i < n; i += 2) {
            res += piles[i];
        }
        return res;
    }
}
