package com.leetcode.array;

import java.util.Arrays;
import java.util.HashMap;

public class _1365_HowManyNumbersAreSmallerThantheCurrentNumber {

    /**
     * When: 03/01/2020
     * @param nums
     * @return
     */
    // 本质就是看这个数在有序的情况下的位置。（可以用hashmap直接记录index的情况）
    // time:O(nlogn) space:O(n)
    public int[] smallerNumbersThanCurrent(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        int n = nums.length;
        int[] res = new int[n];
        int[] copy = nums.clone();
        HashMap<Integer, Integer> map = new HashMap<>();
        Arrays.sort(copy);
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(copy[i])) // 相当于可以区分distinct
                map.put(copy[i], i);
        }
        for (int i = 0; i < n; i++) {
            res[i] = map.get(nums[i]);
        }
        return res;
    }

    // bucket sort
    // time:O(n) space:O(1)
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        int n = nums.length;
        int[] res = new int[n];
        // 利用bucket sort 这种针对于固定的大小的。计数然后累计和来做
        int size = 100;
        int[] count = new int[size + 1];
        for (int num : nums) count[num]++;
        for (int i = 1; i <= size; i++) {
            count[i] += count[i - 1];
        }
        for (int i = 0; i < n; i++) {

            if (nums[i] - 1 >= 0)  // 防止index 不合法
                res[i] = count[nums[i] - 1]; // 除去自己的情况
        }
        return res;
    }
}