package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 06/20/2020
 * @Description: three pointers
 **/
public class _1213_IntersectionofThreeSortedArrays {

    // time:O(len1 + len2 + len3) space:O(max(len1, len2, len3))
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        // three pointers?
        int i = 0, j = 0, k = 0;
        List<Integer> res = new ArrayList<>();
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            int min = Math.min(arr1[i], Math.min(arr2[j], arr3[k]));
            if (arr1[i] == min && arr2[j] == min && arr3[k] == min) {
                res.add(arr1[i]);
            }
            if (arr1[i] == min) {
                i++;
            }
            if (arr2[j] == min) {
                j++;
            }
            if (arr3[k] == min) {
                k++;
            }
        }
        return res;
    }
}
