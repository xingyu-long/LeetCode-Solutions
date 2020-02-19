package com.leetcode;

import java.util.Arrays;

public class test {
    // 先用backtracking看看。
    public int minJumps(int[] arr) {
        if (arr == null || arr.length == 0) return 0;
        int n = arr.length;
        boolean[] visited = new boolean[n];
        int[] memo = new int[n];
        Arrays.fill(memo, -1);
        return dfs(arr, 0, visited, memo);
    }

    public int dfs(int[] arr, int index, boolean[] visited, int[] memo) {
        if (index == arr.length - 1) return 0;
        if (memo[index] != -1) return memo[index];
        int res = Integer.MAX_VALUE;
        if (visited[index]) return Integer.MAX_VALUE;
        visited[index] = true;
        if (index - 1 >= 0 && !visited[index - 1]) {
            int val = dfs(arr, index - 1, visited, memo);
            if (val != Integer.MAX_VALUE) res = Math.min(res, val + 1);
        }
        if (index + 1 < arr.length && !visited[index + 1]) {
            int val = dfs(arr, index + 1, visited, memo);
            if (val != Integer.MAX_VALUE) res = Math.min(res, val + 1);
        }

        for (int i = 0; i < arr.length; i++) {
            if (index != i && !visited[i] && arr[index] == arr[i]) {
                int val = dfs(arr, i, visited, memo);
                if (val != Integer.MAX_VALUE) res = Math.min(res, val + 1);
            }
        }
        visited[index] = false;
        memo[index] = res;
        return res;
    }

    public double angleClock(int hour, int minutes) {
        double dh = hour;
        double dm = minutes;

        double hangle = dh * 360 / 12 + dm / 60 * (360 / 12);
        hangle %= 360;
        // System.out.println(hangle);
        // System.out.println(dm / 60);
        double mangle = dm / 60 * 360;
        if (mangle > hangle) return Math.min(mangle - hangle, 360 - (mangle - hangle));
        return Math.min(hangle - mangle, 360 - (hangle - mangle));
    }


    public static void main(String[] args) {
        test test = new test();
//        int[] nums = new int[]{100,-23,-23,404,100,23,23,23,3,404};
//        System.out.print(test.minJumps(nums));
        int hour = 1;
        int min = 57;

        System.out.print(test.angleClock(hour, min));
    }
}
