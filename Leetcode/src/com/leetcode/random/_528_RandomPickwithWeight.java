package com.leetcode.random;

import java.util.Random;

public class _528_RandomPickwithWeight {
    // eg: w = [1,2,3,4]
    // 可以变成这样的idx数组 [0,1,1,2,2,2,3,3,3,3]
    // sums = [1,3,6,10]
    // 1-indexed  [[1], [2-3], [4-6], [7 - 10]] 相当于分成了四个区间。看随机数掉在哪个地方。
    // 那个[1, n] 还是需要琢磨琢磨

    int[] sums;
    Random random;

    public _528_RandomPickwithWeight(int[] w) {
        sums = new int[w.length];
        sums[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            sums[i] = sums[i - 1] + w[i];
        }
        random = new Random();
    }
    // 一定会有解的，然后返回其index即可
    public int pickIndex() {
        int n = sums.length;
        int left = 0;
        int right = n - 1;
        // 个数肯定不能为0个
        int target = random.nextInt(sums[n - 1]) + 1; // [1 ~ sums[n - 1]]; 表示第几个（从1开始计数的）
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (sums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // 看其插入的位置在哪
        if (sums[left] >= target) {
            return left;
        }
        if (sums[right] < target) {
            return right + 1;
        }
        return right;
    }
}
