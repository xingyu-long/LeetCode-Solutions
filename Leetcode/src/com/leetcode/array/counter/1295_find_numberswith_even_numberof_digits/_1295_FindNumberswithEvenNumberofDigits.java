package com.leetcode.array.counter;

/**
 * _1295_FindNumberswithEvenNumberofDigits
 */
public class _1295_FindNumberswithEvenNumberofDigits {

    // more naive one, using getNum for calculating number of digit.
    // time:O(n * len(n)) space:O(1)
    // Runtime: 1ms
    public int findNumbers(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0)
            return 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            int NumOfDigit = getNum(nums[i]);
            if (NumOfDigit % 2 == 0)
                res++;
        }
        return res;
    }

    public int getNum(int num) {
        int count = 0;
        while (num != 0) {
            count++;
            num /= 10;
        }
        return count;
    }

    // convert to the String first and use the length.
    // time:O(n * len(n)) space:O(1)
    // Runtime: 2ms
    public int findNumbers2(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0)
            return 0;
        int res = 0;
        for (int num : nums) {
            res += (String.valueOf(num).length() % 2 == 0) ? 1 : 0;
        }
        return res;
    }
}