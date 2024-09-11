package com.leetcode.array;

import java.util.Arrays;

/**
 * @Date: 2019/2/12, 2019/7/1, 2019/8/20, 08/25/2020
 * @Description: Graph, array.
 **/
public class _189_RotateArray {
    //time: O(n) space:O(n)
    public static void rotate(int[] nums, int k) {
        //通过求余运算
        int[] temp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            //i + k就表示应该所在的位置，但是可能超出数组长度，所以这里再%即可。
            temp[(i + k) % nums.length] = nums[i];
        }
        //赋值给nums
        System.arraycopy(temp, 0, nums, 0, nums.length);
    }

    /**
     * 思路：通过反转数组的方式，然后局部反转 达到效果。
     *
     * @param nums
     * @param k
     */
    //time:O(n) space:O(1)
    public static void rotate2(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
