/*
 * @Date: 03/24/2021 10:07:23
 * @LastEditTime: 03/24/2021 10:07:58
 * @Description: Greedy, sort
 */
package com.leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _870_AdvantageShuffle {
    public int[] advantageCount(int[] A, int[] B) {
        Arrays.sort(A);
        int n = A.length;
        int[] res = new int[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] - a[0]));
        for (int i = 0; i < n; i++) {
            pq.offer(new int[] { B[i], i });
        }
        int low = 0, high = n - 1;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int index = curr[1], val = curr[0];
            if (A[high] > val)
                res[index] = A[high--];
            else
                res[index] = A[low++];
        }
        return res;
    }
}
