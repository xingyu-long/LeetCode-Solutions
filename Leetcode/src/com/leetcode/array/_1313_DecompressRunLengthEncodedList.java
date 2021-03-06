package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

public class _1313_DecompressRunLengthEncodedList {
    // time:O(n) space:O(n)
    // 利用额外的list来储存
    public int[] decompressRLElist(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; 2 * i + 1 < nums.length; i++) {
            int a = nums[2 * i];
            int b = nums[2 * i + 1];
            for (int j = 0; j < a; j++) {
                list.add(b);
            }
        }
        int[] res = new int[list.size()];
        int k = 0;
        for (int num : list) {
            res[k++] = num;
        }
        return res;
    }

    // time:O(n) space:O(1)
    // 不用额外的list
    public int[] decompressRLElist2(int[] nums) {
        if (nums == null || nums.length == 0) return new int[]{};
        int count = 0;
        for (int i = 0; i < nums.length; i += 2) {
            count += nums[i];
        }
        int[] res = new int[count];
        int k = 0;
        for (int i = 0; i < nums.length; i += 2) {
            for (int j = 0; j < nums[i]; j++) {
                res[k++] = nums[i + 1];
            }
        }
        return res;
    }
}
