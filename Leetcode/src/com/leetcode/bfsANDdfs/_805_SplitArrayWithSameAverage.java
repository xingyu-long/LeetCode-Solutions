package com.leetcode.bfsANDdfs;

import java.util.Arrays;

public class _805_SplitArrayWithSameAverage {
    
    
    
    /**
     * When: 03/01/2020
     * @param A
     * @return
     */
    // https://www.cnblogs.com/grandyang/p/10285531.html
    // sum / n = sum1 / k = sum2 / (n - k)
    public boolean splitArraySameAverage(int[] A) {
        if (A.length == 1) return false;
        int sum = 0;
        for (int num : A) sum += num;
        Arrays.sort(A);
        for (int len = 1; len <= A.length / 2; len++) {
            if ((sum * len) % A.length == 0) {
                if (dfs(A, (sum * len) / A.length, len, 0)) return true;
            }
        }
        return false;
    }

    // 找剩下个数是否可以生成leftSum
    public boolean dfs (int[] nums, int leftSum, int leftNum, int index) {
        if (leftNum == 0) return leftSum == 0;
        if (nums[index] > leftSum / leftNum) return false;
        for (int i = index; i < nums.length - leftNum + 1; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;
            if (dfs(nums, leftSum - nums[i], leftNum - 1, i + 1)) return true;
        }
        return false;
    }
    
}