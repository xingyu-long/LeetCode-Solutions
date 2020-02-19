package com.leetcode.dynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;

public class _1344_JumpGameV {
    // time:O(n * d) space:O(n)
    public int maxJumps(int[] arr, int d) {
        if (arr == null || arr.length == 0) return 0;
        if (arr.length == 1) return 1;
        int n = arr.length;
        boolean[] visited = new boolean[n];
        int max = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, dfs(arr, d, visited, i, map));
        }
        return max;
    }

    // val的值应该是1，然后那个max，应该是选择了dfs中的一条路才在后面+1
    public int dfs(int[] arr, int d, boolean[] visited, int currIndex, HashMap<Integer, Integer> map) {
        if (currIndex < 0 || currIndex >= arr.length) return 0;
        if (visited[currIndex]) return 0;
        if (map.get(currIndex) != null) return map.get(currIndex);

        visited[currIndex] = true;
        int val = 1;
        for (int i = 1; i <= d; i++) {
            if (isValid(arr, currIndex, currIndex + i)) val = Math.max(val, dfs(arr, d, visited, currIndex + i, map) + 1) ;
            if (isValid(arr, currIndex, currIndex - i)) val = Math.max(val, dfs(arr, d, visited, currIndex - i, map) + 1);
        }

        visited[currIndex] = false;
        map.put(currIndex, val);
        return val;
    }
    public boolean isValid(int[] nums, int from, int to) {
        if (from < 0 || from >= nums.length || to < 0 || to >= nums.length) return false;

        int max = nums[from];
        if (from > to) {
            for (int i = to; i < from; i++) {
                if (nums[i] >= max) return false;
            }
        } else {
            for (int i = from + 1; i <= to; i++) {
                if (nums[i] >= max) return false;
            }
        }
        return true;
    }


    // DFS + memo 基本和上面一样，但是要更加的简洁
    // time: O(nd) space:O(n)
    public int maxJumps2(int[] arr, int d) {
        int n = arr.length;
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = Math.max(res, dfs(arr, i, memo, d, n));
        }
        return res;
    }

    public int dfs (int[] arr, int index, int[] memo, int d, int n) {
        if (memo[index] != -1) return memo[index];
        int val = 1;
        // 因为这个单调走的特征，不需要做visited 数组，因为都只能被访问一次。不会往回走。
        for (int j = index + 1; j <= Math.min(n - 1, index + d) && arr[index] > arr[j]; j++) {
            val = Math.max(val, dfs(arr, j, memo, d, n) + 1);
        }

        for (int j = index - 1; j >= Math.max(0, index - d) && arr[index] > arr[j]; j--) {
            val = Math.max(val, dfs(arr, j, memo, d, n) + 1);
        }
        memo[index] = val;
        return val;
    }
}
