package com.leetcode.bitManipulation;

import java.util.HashSet;

public class _260_SingleNumberIII {
    // https://leetcode.com/problems/single-number-iii/discuss/68900/Accepted-C%2B%2BJava-O(n)-time-O(1)-space-Easy-Solution-with-Detail-Explanations
    // 主要是利用高位和地位来区分两组。
    // time:O(n) space:O(1)
    public int[] singleNumber(int[] nums) {
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        diff &= -diff;
        int[] res = new int[]{0, 0};
        for (int num : nums) {
            if ((num & diff) == 0) {
                res[0] ^= num;
            } else {
                res[1] ^= num;
            }
        }
        return res;
    }
}
