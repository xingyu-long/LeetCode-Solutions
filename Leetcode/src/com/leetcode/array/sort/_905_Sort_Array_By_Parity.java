package com.leetcode.array.sort;

public class _905_Sort_Array_By_Parity {
    // 主要是移动偶数到前面，如果是偶数交换到前面即可。
    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length == 0) {
            return new int[] {};
        }
        int index = 0;
        for (int i = 0; i < A.length; i++) {
            if (A[i] % 2 == 0) {
                exch(A, index++, i);
            }
        }
        return A;
    }

    public int[] sortArrayByParity2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        int index = 0;
        int left = 0, right = nums.length - 1;
        while (index <= right) {
            if (nums[index] % 2 == 0) {
                exch(nums, index++, left++);
            } else {
                exch(nums, index, right--);
            }
        }
        return nums;
    }

    public void exch(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
