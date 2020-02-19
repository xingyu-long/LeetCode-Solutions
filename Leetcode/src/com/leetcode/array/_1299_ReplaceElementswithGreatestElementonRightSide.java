package com.leetcode.array;

public class _1299_ReplaceElementswithGreatestElementonRightSide {

    // time:O(n^2) space:O(n)
    public int[] replaceElements(int[] arr) {
        if (arr == null || arr.length == 0) return new int[]{};
        int[] res = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int max = -1;
            for (int j = i + 1; j < arr.length; j++) {
                max = Math.max(max, arr[j]);
            }
            res[i] = max;
        }
        return res;
    }

    // time:O(n) space:O(1)
    public int[] replaceElements2(int[] arr) {
        // 倒着计算每次先加入其max，
        if (arr == null || arr.length == 0) return new int[]{};
        int max = -1;
        for (int i = arr.length - 1; i >= 0; i--) {
            int temp = arr[i];
            arr[i] = max;
            max = Math.max(max, temp);
        }
        return arr;
    }


}
