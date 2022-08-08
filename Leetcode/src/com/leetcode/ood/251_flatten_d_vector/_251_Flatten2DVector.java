package com.leetcode.ood;

/**
 * @Date: 09/26/2020
 * @Description: Flatten
 **/
public class _251_Flatten2DVector {
    int[][] v;
    int row, col;
    // 需要注意有的会是空的，所以需要遍历对比。
    public _251_Flatten2DVector(int[][] v) {
        this.v = v;
        row = col = 0;
    }

    public int next() {
        if (hasNext()) return v[row][col++];
        return -1;
    }

    public boolean hasNext() {
        while (row < v.length) {
            if (col < v[row].length) {
                return true;
            } else {
                row++;
                col = 0;
            }
        }
        return false;
    }
}
