package com.intern.Roblox;

public class NumberOfSubarraysWithmoddNumbers {

    public int findNumber(int[] nums, int m) {
        return atMostM(nums, m) - atMostM(nums, m - 1);
    }
    public int atMostM(int[] nums, int m) {
        int end = 0;
        int start = 0;
        int res = 0;
        while (end < nums.length) {
            if (nums[end] % 2 != 0) m--;
            while (m < 0) {
                if (nums[start] % 2 != 0) m++;
                start++;
            }
            res += end - start + 1;
            end++;
        }
        return res;
    }
}
