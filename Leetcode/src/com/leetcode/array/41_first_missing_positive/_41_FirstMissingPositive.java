package com.leetcode.array;

// LeetCode No. 41
public class _41_FirstMissingPositive {


    /**
     * 13-Feb	1-Jul	6-Nov
     * 利用"桶排序"思想
     * index + 1
     * 每个位置应该满足值等于该索引+1
     * 不满足就交换 （1. 输出第一个不符合该序列的index+1 2. 整个都是符合该序列 则输出nums.length + 1）
     * @param nums
     * @return
     */

    //time:O(n) space:O(1)
    public static int firstMissingPositive(final int[] nums) {
        if (nums == null || nums.length == 0) return 1;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] - 1 >= 0 && nums[i] - 1 < nums.length
                    && nums[i] != nums[nums[i] - 1]) {
                exch(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]) return i + 1;
        }
        return nums.length + 1;
    }

    public static void exch(final int[] nums, final int a, final int b) {
        final int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    public static void main(final String[] args){
        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));
    }
}
