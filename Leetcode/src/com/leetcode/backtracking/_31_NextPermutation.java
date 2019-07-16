package com.leetcode.backtracking;

public class _31_NextPermutation {

    /**
     *  31. Next Permutation
     *  When: 2019/04/30
     *  Review1: 2019/7/16
     *
     * solution:
     * 从后往前找
     * 首先找到第一个小于后一个数的位置。
     * 然后这个位置如果不能找到，就把当前的进行翻转
     * 再寻找大于前面那个的第一个数
     * 交换
     * 然后从firstSmall+1 -> end 翻转
     *
     * test case:
     *    1, 2, 7, 3, 4, 1
     *             ^
     *    1, 2, 7, 3, 4, 1
     *             ^  ^
     *    1, 2, 7, 4, 3, 1
     *
     *    1, 2, 7, 4, 1, 3
     *
     * @param nums
     */
    // 只能反转较大的数 然后变成较小的情况
    // 记得画图分析，语言不好表达
    // time:O(n) space:O(1)
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length == 0) return;

        int firstSmall = -1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstSmall = i;
                break;
            }
        }

        // 就表明这个是最大的情况，所以直接反转最小即可
        if (firstSmall == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        int firstLarge = -1;
        for (int i = nums.length - 1; i > firstSmall; i--) {
            if (nums[i] > nums[firstSmall]) {
                firstLarge = i;
                break;
            }
        }

        swap(nums, firstSmall, firstLarge);
        reverse(nums, firstSmall + 1, nums.length - 1);
        return;
    }


    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int i, int j) {
        while(i < j) {
            swap(nums, i++, j--);
        }
    }
}
