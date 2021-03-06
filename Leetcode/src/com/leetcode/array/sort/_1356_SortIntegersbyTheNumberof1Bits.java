package com.leetcode.array.sort;

import java.util.*;

public class _1356_SortIntegersbyTheNumberof1Bits {
    // 1356. Sort Integers by The Number of 1 Bits
    // When: 02/22/2020
    // time: O(nlogn)
    // space: O(n)

    public int count(int num) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res += num & 1;
            num >>= 1;
        }
        return res;
    }
    public int[] sortByBits(int[] arr) {

        if (arr == null || arr.length == 0) return new int[]{};
        int n = arr.length;
        Integer[] nums = new Integer[n];
        for (int i = 0; i < n; i++) nums[i] = arr[i];

        Comparator comp = new myComp();
        Arrays.sort(nums, comp);

        for (int i = 0; i < arr.length; i++) arr[i] = nums[i];
        // Integer.bitCount(); 可以用这个计算
        return arr;
    }

    class myComp implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            int c1 = count(o1);
            int c2 = count(o2);
            return c1 != c2 ? c1 - c2 : o1 - o2;
        }
    }
}
