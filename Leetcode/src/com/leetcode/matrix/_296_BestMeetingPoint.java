package com.leetcode.matrix;

import java.util.ArrayList;
import java.util.List;

public class _296_BestMeetingPoint {

    //time:O(m*n) space:O(m + n)
    public int minTotalDistance(int[][] grid) {
        if (grid == null || grid.length == 0 ||
                grid[0] == null || grid[0].length == 0) return 0;
        int m = grid.length;
        int n = grid[0].length;

        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        // 分两次加入这样就自然有序了。
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    rows.add(i);
                }
            }
        }
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (grid[i][j] == 1) {
                    cols.add(j);
                }
            }
        }
        return getDis(rows) + getDis(cols);
    }

    public int getDis (List<Integer> nums) {
        int left = 0;
        int right = nums.size() - 1;
        int sum = 0;
        while (left < right) { // 奇数的话也不用动，可以把点选到这，因为不管怎么样都要走动相同的总长度
            sum += nums.get(right) - nums.get(left);
            right--;
            left++;
        }
        return sum;
    }
}
