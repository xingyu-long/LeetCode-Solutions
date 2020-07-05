package com.leetcode.array;

import java.util.Arrays;

public class _135_Candy {
    /**
     *  135. Candy
     *  When:2019/8/3
     *  Difficulty: Hard
     *  Solution:
     *  1. 首先全都置为1
     *  2. 顺序查找后一个比前一个大的，然后加上前面的值
     *  3. 逆序查找前一个比后一个大的，max(当前数，后一个+1) 就是这里没有想到
     * @param ratings
     * @return
     */
    // time:O(n) space:O(n)
    public int candy(int[] ratings) {
        int[] count = new int[ratings.length];
        Arrays.fill(count, 1);
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                count[i] = count[i - 1] + 1;
            }
        }
        for (int i = ratings.length - 2; i >= 0; i--) {
            // 这一步很重要！
            // 可以解决这种情况 例如[1, 6, 10, 8, 7, 3, 2]

            if (ratings[i] > ratings[i + 1]) {
                count[i] = Math.max(count[i], count[i + 1] + 1);
            }
        }
        return Arrays.stream(count).sum();
    }
}
