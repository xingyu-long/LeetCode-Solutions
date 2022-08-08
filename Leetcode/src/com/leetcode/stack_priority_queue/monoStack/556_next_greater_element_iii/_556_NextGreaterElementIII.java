/*
 * @Date: 12/23/2020 11:08:00
 * @LastEditTime: 12/23/2020 11:08:31
 * @Description: Same with next permutation.
 */
package com.leetcode.stack_priority_queue.monoStack;

public class _556_NextGreaterElementIII {
    public int nextGreaterElement(int n) {
        char[] nums = String.valueOf(n).toCharArray();
        int firstSmall = -1, firstLarge = -1;
        int count = nums.length;
        for (int i = count - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                firstSmall = i;
                break;
            }
        }
        if (firstSmall == -1) {
            return -1;
        }
        for (int i = count - 1; i > firstSmall; i--) {
            if (nums[i] > nums[firstSmall]) {
                firstLarge = i;
                break;
            }
        }

        exch(nums, firstSmall, firstLarge);
        reverse(nums, firstSmall + 1, count - 1);
        long res = Long.valueOf(new String(nums));
        if (res > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) res;
    }

    private void exch(char[] chs, int i, int j) {
        char ch = chs[i];
        chs[i] = chs[j];
        chs[j] = ch;
    }

    private void reverse(char[] chs, int i, int j) {
        while (i < j) {
            exch(chs, i++, j--);
        }
    }
}
