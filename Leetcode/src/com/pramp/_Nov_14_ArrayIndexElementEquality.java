package com.pramp;

public class _Nov_14_ArrayIndexElementEquality {

    // 相当于是找num[i] == i的情况。
    static int indexEqualsValueSearch(int[] arr) {
        // your code goes here
        if (arr == null || arr.length == 0) return -1;
        // binary search. O(n) -> O(logN)
        int left = 0;
        int right = arr.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2; // in case of oveflow;
            if (arr[mid] >= mid) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (arr[left] == left) return left;
        if (arr[right] == right) return right;
        return -1;
    }
}
