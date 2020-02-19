package com.leetcode.binarySearch;

import java.util.Arrays;
import java.util.Comparator;

public class _354_RussianDollEnvelopes {

    // 将其转化为LIS问题
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0) return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            @Override
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) return e1[0] - e2[0];
                else return e2[1] - e1[1];
            }
        });
        int res = 0; // 需要先导入一个数据
        int[] sorted = new int[envelopes.length];
        sorted[res++] = envelopes[0][1];
        for (int i = 1; i < envelopes.length; i++) {
            // 这里的right也是res - 1！
            int index = findIndex(sorted, 0, res - 1, envelopes[i][1]);
            sorted[index] = envelopes[i][1];
            if (index == res) {
                res++;
            }
        }
        return res;
    }

    public int findIndex(int[] nums, int left, int right, int target) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (target > nums[right]) return right + 1;
        if (target <= nums[left]) return left;
        else return right;
    }
}
