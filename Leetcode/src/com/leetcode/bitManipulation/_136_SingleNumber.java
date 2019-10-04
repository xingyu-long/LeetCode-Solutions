package com.leetcode.bitManipulation;

import java.util.HashSet;

public class _136_SingleNumber {

    /**
     * 136. Single Number
     * When: 2019/06/17
     * review1：2019/6/28
     * <p>
     * solution:
     * (1) 利用hashset的性质 但是这里的contains操作消耗O(n) 所以总的是 O(n^2)
     * (2) 利用异或运算
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        //使用hashSet
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            } else {
                set.add(nums[i]);
            }
        }
        int res = 0;
        for (int in : set) {
            res = in;
        }
        return res;
    }

    // time:O(n) space:O(1)
    // 也是与missing number 采取的 ^同样的原理 a^b^b = a;
    public static int singleNumber2(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 2, 1};
        System.out.print(singleNumber(nums));
    }
}
