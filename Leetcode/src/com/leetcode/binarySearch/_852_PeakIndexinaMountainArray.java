package com.leetcode.binarySearch;

/**
 * @Date: 06/22/2020
 * @Description: Find Peak, Binary Search
 **/
public class _852_PeakIndexinaMountainArray {

    public int peakIndexInMountainArray(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        /*
             /\
            /
           /
        */
        int left = 0, right = A.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (A[mid - 1] > A[mid]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (A[left] > A[right]) {
            return left;
        }
        if (A[left] < A[right]) {
            return right;
        }
        return 0;
    }
}
