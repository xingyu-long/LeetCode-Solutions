/*
 * @Date: 06/03/2021 10:31:59
 * @LastEditTime: 06/03/2021 10:32:52
 * @Description: Array
 */
package com.leetcode.array;

import java.util.Arrays;

public class _1465_MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {
    public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        int mod = (int) (Math.pow(10, 9) + 7);
        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);
        long hCut = maxCut(horizontalCuts, h);
        long vCut = maxCut(verticalCuts, w);
        long res = (hCut * vCut) % mod;
        return (int) res;
    }

    private long maxCut(int[] arr, int len) {
        long max = 0;
        for (int i = 0; i <= arr.length; i++) {
            if (i == 0) {
                max = Math.max(max, arr[i] - 0);
            } else if (i == arr.length) {
                max = Math.max(max, len - arr[i - 1]);
            } else {
                max = Math.max(max, arr[i] - arr[i - 1]);
            }
        }
        return max;
    }
}
