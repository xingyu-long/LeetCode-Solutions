/*
 * @Date: 10/21/2020 10:33:19
 * @LastEditTime: 12/05/2020 11:35:30
 * @Description: Greedy
 */
package com.leetcode.greedy;

public class _605_CanPlaceFlowers {
    // 之前应该做过，但是这次做的时候没有想出来这个处理边界的方法
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int len = flowerbed.length;
        for (int i = 0; i < len && n > 0; i++) {
            if (flowerbed[i] == 0) {
                if (i == 0 && i + 1 < len && flowerbed[i + 1] == 1) continue;
                if (i == len - 1 && i - 1 >= 0 && flowerbed[i - 1] == 1) continue;
                if (i - 1 >= 0 && flowerbed[i - 1] == 1) continue;
                if (i + 1 < len && flowerbed[i + 1] == 1) continue;
                n--;
                flowerbed[i] = 1;
            }
        }
        return n == 0;
    }
}
