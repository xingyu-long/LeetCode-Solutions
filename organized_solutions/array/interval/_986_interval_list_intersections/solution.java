/*
 * @Date: 05/11/2020 17:42:50
 * @LastEditTime: 06/26/2022 21:42:01
 * @Description: Interval
 */
package com.leetcode.array.interval;

import java.util.ArrayList;
import java.util.List;

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
            list.add(new int[] { left, right });

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

    // 类似于1229的解法
    public int[][] intervalIntersection2(int[][] firstList, int[][] secondList) {
        int i = 0, j = 0;
        int len1 = firstList.length, len2 = secondList.length;
        List<int[]> res = new ArrayList<>();
        while (i < len1 && j < len2) {
            int start = Math.max(firstList[i][0], secondList[j][0]);
            int end = Math.min(firstList[i][1], secondList[j][1]);
            // 加入每一个可能的intersection area
            if (end - start >= 0) {
                res.add(new int[]{start, end});
            }
            if (firstList[i][1] > secondList[j][1]) {
                j++;
            } else {
                i++;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
