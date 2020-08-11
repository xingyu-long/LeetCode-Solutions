package com.leetcode.array.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 08/04/2020
 * @Description: Sort, similar with selection sort.
 **/
public class _969_PancakeSorting {
    public List<Integer> pancakeSort(int[] A) {
        if (A == null || A.length == 0) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        int n = A.length;
        for (int i = n - 1; i > 0; i--) {
            int max = A[i];
            int index = i;
            for (int j = i; j >= 0; j--) {
                if (A[j] > max) {
                    max = A[j];
                    index = j;
                }
            }
            // already find the max;
            flip(A, index + 1);
            res.add(index + 1);
            flip(A, i + 1);
            res.add(i + 1);
        }
        return res;
    }

    // K as 1-indexed.
    public void flip(int[] A, int k) {
        int left = 0, right = k - 1;
        while (left < right) {
            int temp = A[left];
            A[left] = A[right];
            A[right] = temp;
            left++;
            right--;
        }
    }
}
