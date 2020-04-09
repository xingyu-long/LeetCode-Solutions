package com.leetcode.dynamicProgramming;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _1043_PartitionArrayforMaximumSum {
    public int maxSumAfterPartitioning(int[] A, int K) {
        // [1,15,7,9,2,5,10]
        // 15,15,15,15 一开始以为是slding window。 但不是，还是属于分长度问题
        if (A == null || A.length == 0) return 0;
        int n = A.length;
        // 分割为最长值
        int[][] memo = new int[n + 1][K + 1];
        for (int[] arr : memo) {
            Arrays.fill(arr, -1);
        }
        return dfs(A, n - 1, K, memo);
    }

    public int dfs(int[] A, int j, int k, int[][] memo) {
        if (j < 0) return 0;
        if (j == 0) return A[j];
        if (memo[j][k] != -1) return memo[j][k];
        int res = Integer.MIN_VALUE;
        int max = A[j];
        for (int i = 1; i <= k; i++) {
            if (j - i + 1 < 0) continue;
            max = Math.max(max, A[j - i + 1]); // 比较值的时候需要注意
            res = Math.max(res, dfs(A, j - i, k, memo) + i * max);
        }
        memo[j][k] = res;
        return res;
    }

    @Test
    public static void main(String[] args) {
        _1043_PartitionArrayforMaximumSum partition = new _1043_PartitionArrayforMaximumSum();
        int[] nums = {1,15,7,9,2,5,10};
        int k = 3;
        assertEquals(84, partition.maxSumAfterPartitioning(nums, 3));
        assertEquals(75, partition.maxSumAfterPartitioning(nums, 2));
        assertEquals(90, partition.maxSumAfterPartitioning(nums, 4));
    }
}
