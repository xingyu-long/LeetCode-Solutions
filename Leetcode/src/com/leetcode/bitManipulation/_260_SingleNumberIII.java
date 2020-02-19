package com.leetcode.bitManipulation;

import java.util.HashSet;

public class _260_SingleNumberIII {
    // time:O(n) space:O(n)
    public int[] singleNumber(int[] nums) {
        // 利用hashSet
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.contains(num)) {
                set.add(num);
            } else {
                set.remove(num);
            }
        }
        int[] res = new int[set.size()];
        int i = 0;
        for (int num : set) {
            res[i++] = num;
        }
        return res;
    }
}
