package com.leetcode.backtracking;

import java.util.HashMap;

public class JumpGamesIII {
    public boolean canReach(int[] arr, int start) {
        if (arr[start] == 0) return true;
        boolean[] visited = new boolean[arr.length];
        HashMap<Integer, Boolean> map = new HashMap<>();
        return helper(arr, start, visited, map);
    }


    public boolean helper(int[] arr, int start, boolean[] visited, HashMap<Integer, Boolean> map) {
        if (map.get(start) != null) return map.get(start);
        if (start >= arr.length || start < 0 || visited[start]) return false;
        if (arr[start] == 0) return true;
        if (start + arr[start] < arr.length) {
            // check的时候直接return了。
            visited[start] = true;
            int newStart = start + arr[start];
            if (helper(arr, newStart, visited, map)) {
                map.put(start, true);
                return true;
            }
            visited[start] = false;
        }
        if (start - arr[start] >= 0) {
            visited[start] = true;
            int newStart = start - arr[start];
            if (helper(arr, newStart, visited, map)) {
                map.put(start, true);
                return true;
            }
            visited[start] = false;
        }
        map.put(start, false);
        return false;
    }

    public static void main(String[] args) {
        JumpGamesIII games = new JumpGamesIII();
        int[] nums = new int[]{4,2,3,0,3,1,2};
        int start = 0;
        System.out.println(games.canReach(nums, start));
    }
}
