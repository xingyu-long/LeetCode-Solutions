package com.leetcode.array.counter;

public class _1151_MinimumSwapstoGroupAll1sTogether {
    // 利用固定大小的window size去找含有1最多的情况，之后做差即可。
    public int minSwaps(int[] data) {
        if (data == null || data.length == 0) {
            return 0;
        }

        int ones = 0;
        for (int num : data) {
            if (num == 1) ones++;
        }

        int windowSize = ones;
        int maxSumOfOne = 0;
        int sum = 0;
        int start = 0, end = 0;
        int n = data.length;
        while (end < n) {
            sum += data[end];
            while (end - start + 1 > windowSize) {
                sum -= data[start];
                start++;
            }
            maxSumOfOne = Math.max(maxSumOfOne, sum);
            end++;
        }
        return windowSize - maxSumOfOne;
    }
}
