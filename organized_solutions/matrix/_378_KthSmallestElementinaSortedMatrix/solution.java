package com.leetcode.matrix;

import java.util.PriorityQueue;

public class _378_KthSmallestElementinaSortedMatrix {
    // 利用binary search，搜索比target小的个数有多少。最后的判断条件需要注意。
    // time: O((m + n) * log(matrix[n - 1][n - 1])), space: O(1)
    public int kthSmallest(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) return 0;
        int n = matrix.length;
        int left = matrix[0][0];
        int right = matrix[n - 1][n - 1];
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (count(matrix, mid) >= k) right = mid;
            else left = mid;
        }
        // 表示加上当前这个选定的数，因为count是指比这个小的个数。
        if (count(matrix, right) + 1 <= k) return right;
        else return left;
    }

    // 从左下角开始找
    public int count(int[][] matrix, int target) {
        int n = matrix.length;
        int res = 0;
        int i = n - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] < target) {
                res += i + 1; // 为啥是i+1? 表示当前列都小于它这个，就直接加起来
                j++;
            } else i--;
        }
        return res;
    }

    // 利用priorityQueue 常见的是构建最大堆，然后大于k个就poll time:NlogK
    public int kthSmallest2(int[][] matrix, int k) {
        // it might contains duplicates.
        // max pq nlogk
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) return 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                pq.offer(matrix[i][j]);
                if (pq.size() > k) pq.poll();
            }
        }
        return pq.peek();
    }

    // 当然也有KlogK的做法，这样就只是最多读入k个，构造的是最小堆
    public class Node {
        int row;
        int col;

        Node (int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int kthSmallest3(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 ||
                matrix[0] == null || matrix[0].length == 0) return 0;
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> matrix[a.row][a.col] - matrix[b.row][b.col]);
        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m && i < k; i++) pq.offer(new Node(i, 0));
        int count = 0, res = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            res = matrix[node.row][node.col];
            if (++ count == k) break;
            node.col++;
            if (node.col < n) pq.offer(node);
        }
        return res;
    }

}
