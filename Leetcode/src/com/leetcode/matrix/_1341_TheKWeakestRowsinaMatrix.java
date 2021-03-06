package com.leetcode.matrix;

import java.util.Collections;
import java.util.PriorityQueue;

public class _1341_TheKWeakestRowsinaMatrix {
    // sorting, 因为1总是出现在0前面，所以count那部分可以用binary search进行优化
    // time:O(n * m),
    // binary search: O(n * logM)
    public int[] kWeakestRows(int[][] mat, int k) {
        if (mat == null || mat.length == 0 || mat[0] == null || mat[0].length == 0) return new int[]{};
        PriorityQueue<Row> pq = new PriorityQueue<>(k, Collections.reverseOrder());
        int m = mat.length;
        int n = mat[0].length;
        int[] res = new int[k];
        for (int i = 0; i < m; i++) {
            // use binary search to optimize
            int count = find(mat[i], 1) + 1;
            pq.offer(new Row(count, i));
            if (pq.size() > k) pq.poll();
        }
        int i = k - 1;
        while (!pq.isEmpty()) {
            res[i--] = pq.poll().row;
        }
        return res;
    }

    public class Row implements Comparable<Row> {
        int num;
        int row;

        public Row(int n, int r) {
            num = n;
            row = r;
        }

        @Override
        public int compareTo(Row o) {
            if (this.num != o.num) return this.num - o.num;
            return this.row - o.row;
        }
    }

    public int find(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) left = mid;
            else right = mid;
        }
        if (nums[right] == 1) return right;
        if (nums[left] == 1) return left;
        return -1;
    }

}
