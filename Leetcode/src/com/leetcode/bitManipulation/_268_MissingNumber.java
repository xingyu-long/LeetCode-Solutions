package com.leetcode.bitManipulation;

import java.util.HashSet;

public class _268_MissingNumber {

    /**
     *
     * @param nums
     * @return
     */
    // time:O(n) space:O(n)
    public int missingNumber(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        // 使用HashSet
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int n = nums.length + 1;//因为原有这个多个数 然后数字是 0 ~ nums.length;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) {
                res = i;
                break;
            }
        }
        return res;
    }

    //XOR操作 长度 * 坐标 * 值 就能找出缺少的
    // time:O(n) space:O(1)
    public int missingNumber2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int res  = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= i;
            res ^= nums[i];
        }
        return res;
    }
}
