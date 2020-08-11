package com.leetcode.backtracking;

/**
 * @Date: 04/25/2020
 * @Description: DFS
 **/
public class _1306_JumpGameIII {

    // 其实有点类似于integer replacement的感觉。 这里的visited充当memo用了，不用写memo
    // time:O(n) 用visited数组了 所以每个元素只是被访问一次
    public boolean canReach(int[] arr, int start) {
        if (arr == null || arr.length == 0) {
            return false;
        }
        int n = arr.length;
        boolean[] visited = new boolean[n];
        return canReach(arr, start, visited);
    }

    private boolean canReach(int[] arr, int start, boolean[] visited) {
        if (start < 0 || start >= arr.length) {
            return false;
        }
        if (visited[start]) {
            return false;
        }
        if (arr[start] == 0) {
            return true;
        }
        visited[start] = true;
        if (canReach(arr, start + arr[start], visited)) {
            return true;
        }
        if (canReach(arr, start - arr[start], visited)) {
            return true;
        }
        visited[start] = false;
        return false;
    }
}
