package com.leetcode.array.interval;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 05/11/2020
 * @Description: interval
 **/
public class _986_IntervalListIntersections {
    // time: O(lenA + lenB) space:O(intersection * 2)
    public int[][] intervalIntersection(int[][] A, int[][] B) {
        List<int[]> list = new ArrayList<>();
        int i = 0, j = 0;
        int lenA = A.length, lenB = B.length;
        while (i < lenA && j < lenB) {
            if (A[i][0] > B[j][1]) {
                // 当前j都在i前面
                j++;
                continue;
            }

            if (A[i][1] < B[j][0]) {
                i++;
                continue;
            }

            // intersection
            int left = Math.max(A[i][0], B[j][0]);
            int right = Math.min(A[i][1], B[j][1]);
            list.add(new int[]{left, right});

            // move;
            if (A[i][1] > B[j][1]) {
                j++;
            } else {
                i++;
            }
        }
        int[][] res = new int[list.size()][2];
        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }
        return res;
    }
}
