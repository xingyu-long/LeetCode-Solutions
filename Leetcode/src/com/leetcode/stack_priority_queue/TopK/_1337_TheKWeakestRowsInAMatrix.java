/*
 * @Date: 02/15/2021 09:18:59
 * @LastEditTime: 02/15/2021 09:31:26
 * @Description: PriorityQueue, Binary Search
 */
package com.leetcode.stack_priority_queue.TopK;

import java.util.PriorityQueue;

public class _1337_TheKWeakestRowsInAMatrix {
    // time: m * (nlogn + mlogK)
    public int[] kWeakestRows(int[][] mat, int k) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) {
            return new int[] {};
        }
        int m = mat.length, n = mat[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[0] != b[0] ? b[0] - a[0] : b[1] - a[1]));
        for (int i = 0; i < m; i++) {
            // 因为说了1总是会出现在0的前面所以可以用二分搜索来优化
            int solider = countOnes(mat[i]);
            // for (int j = 0; j < n; j++) {
            //     if (mat[i][j] == 1) {
            //         solider++;
            //     }
            // }
            pq.offer(new int[] { solider, i });
            if (pq.size() > k)
                pq.poll();
        }
        int[] res = new int[k];
        for (int i = k - 1; i >= 0; i--) {
            int[] curr = pq.poll();
            res[i] = curr[1];
        }
        return res;
    }

    private int countOnes(int[] rows) {
        int left = 0, right = rows.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (rows[mid] == 0) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (rows[right] == 1) return right + 1;
        if (rows[left] == 1) return left + 1;
        return 0;
    }
}
