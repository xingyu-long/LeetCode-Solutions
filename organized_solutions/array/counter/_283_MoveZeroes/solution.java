package com.leetcode.array.counter;

public class _283_MoveZeroes {

    /**
     *  283. Move Zeroes
     *  When:2019/8/4
     *  review1:2019/8/23
     *  Difficulty: Easy
     *  solution: 利用一个坐标来记录非0，并且自增和赋值。在此之后就是0
     * @param nums
     */
    // time: O(n) space:O(1)
    public static void moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int indexOfNonZero = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[indexOfNonZero++] = nums[i];
            }
        }
        for (int i = indexOfNonZero; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    // 利用坐标j来记录0的地方，然后一直把它交换
    public void moveZeroes2(int[] nums) {
        if (nums == null || nums.length == 0) return;
        for (int i = 0, j = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j++] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,0,0,0,1};
        moveZeroes(nums);
    }
}
