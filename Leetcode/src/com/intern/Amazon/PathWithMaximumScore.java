package com.intern.Amazon;

public class PathWithMaximumScore {

    private static int res = Integer.MIN_VALUE;
    public static int findMaxInMin(int[][] nums) {
        if (nums == null || nums.length == 0 ||
                nums[0] == null || nums[0].length == 0) return -1;
        int localMin = Integer.MAX_VALUE;
        boolean[][] visited = new boolean[nums.length][nums[0].length];
        dfs(nums, 0, 0, localMin, visited);
        return res;
    }

    public static void dfs(int[][] nums, int row, int col, int localMin, boolean[][] visited) {
        if (row < 0 || row >= nums.length ||
                col < 0 || col >= nums[0].length || visited[row][col]) return;
        localMin = Math.min(localMin, nums[row][col]);
        visited[row][col] = true;
        if (row == nums.length - 1 && col == nums[0].length - 1) {
            //到达了终点
            res = Math.max(res, localMin);
        }
        dfs(nums, row, col + 1, localMin, visited);
        dfs(nums, row + 1, col, localMin, visited);
        visited[row][col] = false;
    }

    public static void main(String[] args) {
        int[][] nums = { {6, 7, 8},
                         {5, 4, 2},
                         {8, 7, 6}};
        System.out.println(findMaxInMin(nums));
    }
}
