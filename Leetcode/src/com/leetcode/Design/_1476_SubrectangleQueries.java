package com.leetcode.design;

import java.util.LinkedList;
import java.util.List;

/**
 * @Date: 06/23/2020
 * @Description: subarray,
 **/
public class _1476_SubrectangleQueries {

    private int[][] rect;
    private List<int[]> data;

    // space:O(n)
    public _1476_SubrectangleQueries(int[][] rectangle) {
        rect = rectangle;
        data = new LinkedList<>();
    }

    // time:O(1)
    public void updateSubrectangle(int row1, int col1, int row2, int col2, int newValue) {
        data.add(new int[]{row1, col1, row2, col2, newValue});
    }

    // time: len(data)
    public int getValue(int row, int col) {
        // System.out.println("row = " + row + " col = " + col);
        for (int i = data.size() - 1; i >= 0; i--) {
            int[] curr = data.get(i);
            // System.out.println(Arrays.toString(curr));
            if (curr[0] <= row && row <= curr[2] && curr[1] <= col && col <= curr[3]) {
                return curr[4];
            }
        }
        return rect[row][col];
    }
}
