package com.leetcode.math;

/**
 * _360_SortTransformedArray
 */
public class _360_SortTransformedArray {

    // 分为a > 0, a < 0, a = 0(合并为>=)
    public static int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int n = nums.length;
        int[] res = new int[n];
        int start = 0;
        int end = n - 1;
        int index = (a >= 0) ? n - 1 : 0;
        while (start <= end) {
            int startNum = getNum(nums[start], a, b, c);
            int endNum = getNum(nums[end], a, b, c);
            if (a >= 0) {
                if (startNum >= endNum) {
                    res[index--] = startNum;
                    start++;
                } else {
                    res[index--] = endNum;
                    end--;
                }
            } else {
                if (startNum >= endNum) {
                    res[index++] = endNum;
                    end--;
                } else {
                    res[index++] = startNum;
                    start++;
                }
            }
        }
        return res;
    }

    public static int getNum(int x, int a, int b, int c) {
        return a * x * x + b * x + c;
    }

    public static void main(String[] args) {
        int[] nums = {-4,-2,2,4};
        int a = 1, b = 3, c = 5;
        int[] res = sortTransformedArray(nums, a, b, c);
        for (int num : res) {
            System.out.print(num + " ");
        }

    }
}