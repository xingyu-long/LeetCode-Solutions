package com.leetcode.bit_manipulation;

import java.util.HashSet;

/**
 * @Date: 07/18/2020
 * @Description: bit,
 **/
public class _268_MissingNumber {
    // 这样和find first positive一样
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] < nums.length && nums[i] != nums[nums[i]]) {
                exch(nums, i, nums[i]);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i]) return i;
        }
        return nums.length;
    }

    private void exch(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    //XOR操作 长度 * 坐标 * 值 就能找出缺少的 原理是什么？？？
    // time:O(n) space:O(1)
    // 利用的是 a ^ b ^ b = a的原理 因为都是一一对应的，只有缺少的那个部分会被选出来
    // index 0, 1, 2, 3
    //       3, 0, 1  x
    // 所以对应的就是x = 2;
    public int missingNumber2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }
}
