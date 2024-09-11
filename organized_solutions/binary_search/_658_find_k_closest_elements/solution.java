package com.leetcode.binary_search;

import java.util.LinkedList;
import java.util.List;

/**
 * @Date: 05/24/2020
 * @Description: binary search, two pointer
 **/
public class _658_FindKClosestElements {
    // two pointer
    // time: O(n) space: O(k)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int left = 0, right = arr.length - 1;
        while (right - left + 1 > k) {
            if (Math.abs(arr[right] - x) < Math.abs(arr[left] - x)) {
                left++;
            } else {
                right--;
            }
        }
        // int index
        List<Integer> res = new LinkedList<>();
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    // time:O(logN) space:O(k)
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int left = 0, right = arr.length - k;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // int index
        List<Integer> res = new LinkedList<>();
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}
