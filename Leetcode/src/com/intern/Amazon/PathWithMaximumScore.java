package com.intern.Amazon;

public class PathWithMaximumScore {

    // ** 是否需要用dp？
    private static int res;
    public static int findMaxInMin(int[][] nums) {
        if (nums == null || nums.length == 0 ||
                nums[0] == null || nums[0].length == 0) return 0;
        int localMin = Integer.MAX_VALUE;
        res = Integer.MIN_VALUE;
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

    // DP
    public static int findMaxInMin2(int[][] nums) {
        if (nums == null || nums.length == 0 ||
                nums[0] == null || nums[0].length == 0) return 0;
        for (int i = 1; i < nums[0].length; i++) {
            nums[0][i] = Math.min(nums[0][i], nums[0][i - 1]);
        }

        for (int i = 1; i < nums.length; i++) {
            nums[i][0] = Math.min(nums[i][0], nums[i - 1][0]);
        }

        for (int i = 1; i < nums.length; i++) {
            for (int j = 1; j < nums[0].length; j++) {
                nums[i][j] = Math.max(Math.min(nums[i - 1][j], nums[i][j]),
                        Math.min(nums[i][j-1], nums[i][j]));
            }
        }
        return nums[nums.length - 1][nums[0].length - 1];
    }
    public static void main(String[] args) {
        int[][] nums = {{5, 1, 7}, {4, 8, 5}};
        int[][] nums2 = {{1, 9, 9}, {9, 9, 9}, {9, 9, 9}};
        int[][] nums3 = {{10, 7, 3}, {12, 10, 0}, {1, 2, 8}};
        int[][] nums4 = {{20, 20, 3}, {20, 3, 20}, {3, 20, 20}};

        System.out.println(findMaxInMin(nums) + " " + findMaxInMin2(nums));
        System.out.println(findMaxInMin(nums2) + " " + findMaxInMin2(nums2));
        System.out.println(findMaxInMin(nums3) + " " + findMaxInMin2(nums3));
        System.out.println(findMaxInMin(nums4) + " " + findMaxInMin2(nums4));
    }
}
