package com.leetcode.math.sum;

import java.util.Hashtable;
import java.util.Map;

public class _167_TwoSumIISortedArray {

    /**
     *  167. Two Sum II - Input array is sorted
     *  When: 2019/04/09
     *  Review1: 2019/8/4
     *
     * solution:
     * 1. 利用hashmap
     * 2. 利用two pointer approach
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return new int[]{-1, -1};
        }
        // Map<Integer, Integer> map = new Hashtable<>();
        Map<Integer, Integer> map = new Hashtable<>();

        for (int i = 0; i < numbers.length; i++) {
            int p = target - numbers[i];
            if (map.containsKey(p)) {
                return new int[]{map.get(p), i + 1};
            }
            map.put(numbers[i], i + 1);
        }
        return new int[]{};
    }

    /**
     * 前后扫描，得到其解法（只是正对于有序的情况才能用这样的）
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoPointerTwoSum(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) {
            return new int[]{-1, -1};
        }
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                return new int[]{i + 1, j + 1};
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                j--;
            }
        }
        // 不会出现同样的i，j因为题目说了不会复用
        return new int[]{};
    }
}
