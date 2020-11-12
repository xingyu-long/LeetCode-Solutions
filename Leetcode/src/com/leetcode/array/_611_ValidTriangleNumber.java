package com.leetcode.array;

import java.util.Arrays;

public class _611_ValidTriangleNumber {
    // time: O(N^2 logN)
    public int triangleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0, n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = nums[i] + nums[j];
                // 1,2,3,4,5
                // i j r
                int left = j + 1, right = n - 1;
                while (left + 1 < right) {
                    int mid = left + (right - left) / 2;
                    // 找到最右边小于sum的情况
                    if (sum > nums[mid]) {
                        left = mid;
                    } else {
                        right = mid;
                    }
                }
                if (right >= n || left >= n)
                    continue; // [0, 1, 0]
                if (nums[right] < sum) {
                    res += right - j;
                } else if (nums[left] < sum) {
                    res += left - j;
                }
            }
        }
        return res;
    }

    // time: O(N^2)
    public int triangleNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int res = 0, n = nums.length;
        Arrays.sort(nums);
        // 因为升序，所以直接找两数之和大于第三边即可
        // 1,2,3,4,5,6,
        // i j
        // 并且这里找的个数是 以j为右顶点，左边j-i个都符合条件。
        // 倒着来找，这样子就可以符合两边之和大于第三边的同时，两边之差也小于第三边。
        for (int i = n - 1; i >= 2; i--) {
            int left = 0, right = i - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum > nums[i]) {
                    res += right - left;
                    right--;
                } else {
                    left++;
                }
            }
        }
        return res;
    }
}