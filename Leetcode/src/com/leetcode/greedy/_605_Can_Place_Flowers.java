package com.leetcode.greedy;

public class _605_Can_Place_Flowers {
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
