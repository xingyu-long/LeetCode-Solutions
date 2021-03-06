/*
 * @Date: 10/15/2020 11:44:42
 * @LastEditTime: 02/25/2021 09:27:26
 * @Description: Sort
 */
package com.leetcode.array.sort;

import java.util.Arrays;

public class _581_Shortest_Unsorted_Continuous_Subarray {
    // 利用额外的一个已经排序的数组做对比，能够对上的部分即是有序的部分，只需要向前向后扫描一遍即可。
    // 这方法太妙了。。
    public int findUnsortedSubarray(int[] nums) {
        int[] copy = nums.clone();
        Arrays.sort(copy);
        int i = 0, j = nums.length - 1;
        int n = nums.length;
        while (i < n && nums[i] == copy[i]) {
            i++;
        }
        while (j > i && nums[j] == copy[j]) {
            j--;
        }
        return j - i + 1;
    }
}
