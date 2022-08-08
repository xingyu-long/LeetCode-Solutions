package com.leetcode.binary_search;

public class _875_KokoEatingBananas {

    /**
     * 875. Koko Eating Bananas
     * When:2019/10/24
     * Difficulty: Medium
     * @param piles
     * @param H
     * @return
     */
    // 相当于找到第一个可以满足H<=8然后最小的数
    public int minEatingSpeed(int[] piles, int H) {
        int left = 1;
        int right = (int) Math.pow(10, 9);
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (canEatAll(piles, mid, H)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        //check 哪一个更小
        if (canEatAll(piles, left, H)) return left;
        else return right;
    }

    public boolean canEatAll(int[] piles, int k, int H) {
        int count = 0;
        for (int pile : piles) {
            count += pile / k;
            if (pile % k != 0) count++; // 剩下的还要花一个小时完成
        }
        return count <= H;
    }
}
