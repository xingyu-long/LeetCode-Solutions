/*
 * @Date: 08/11/2020 18:07:14
 * @LastEditTime: 01/31/2021 09:33:39
 * @Description: Simulation
 */
package com.leetcode.backtracking;

public class _31_NextPermutation {

    /**
     * solution:
     * 从"后往前找"
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
    // 想错了，应该找firstSmall并且倒着走
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
        // 尽量找的时候大于firstSmall 但最小的情况 因为firstSmall后面不会存在 升序的情况！
        for (int i = nums.length - 1; i > firstSmall; i--) {
            if (nums[i] > nums[firstSmall]) {
                firstLarge = i;
                break;
            }
        }
        swap(nums, firstSmall, firstLarge);
        reverse(nums, firstSmall + 1, nums.length - 1);
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
