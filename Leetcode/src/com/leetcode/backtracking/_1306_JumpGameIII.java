package com.leetcode.backtracking;

/**
 * @Date: 04/25/2020
 * @Description: DFS
 **/
public class _1306_JumpGameIII {

    // 其实有点类似于integer replacement的感觉。 这里的visited充当memo用了，不用写memo
    // time:O(n) 用visited数组了 所以每个元素只是被访问一次
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        if (arr == null || arr.length == 0) {
            return false;
        }
        boolean[] visited = new boolean[n];
        return dfs(arr, start, visited);
    }


    public boolean dfs(int[] arr, int start, boolean[] visited) {
        if (start >= arr.length || start < 0) {
            return false;
        }
        if (arr[start] == 0) {
            return true;
        }
        if (visited[start]) {
            return false;
        }
        visited[start] = true;
        return dfs(arr, start + arr[start], visited) || dfs(arr, start - arr[start], visited);
    }
}
