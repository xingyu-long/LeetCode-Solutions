/*
 * @Date: 01/06/2021 10:03:09
 * @LastEditTime: 01/06/2021 10:05:20
 * @Description: Array, Binary Search
 */
package com.leetcode.array;

public class _1539_KthMissingPositiveNumber {
    public int findKthPositive(int[] arr, int k) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int curr = 1, count = k;
        int index = 0, n = arr.length;
        while (index < n) {
            if (curr == arr[index]) {
                index++;
            } else {
                count--;
                if (count == 0)
                    return curr;
            }
            curr++;
        }
        return n + k;
    }
}
