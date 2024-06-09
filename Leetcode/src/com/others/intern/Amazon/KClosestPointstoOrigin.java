package com.intern.Amazon;

import java.util.Comparator;
import java.util.PriorityQueue;

public class KClosestPointstoOrigin {
    //* 需要注意N=0和M=N的edge case.
    public int[][] kClosest(int[][] points, int K) {
        if (points == null || points.length == 0 ||
                points[0] == null || points[0].length == 0) return new int[][]{{}};
        int[][] res = new int[K][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>(K, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                return (int)(Math.pow(Math.abs(a[0]), 2) + Math.pow(Math.abs(a[1]), 2)
                        - Math.pow(Math.abs(b[0]), 2) - Math.pow(Math.abs(b[1]), 2));
            }
        });
        for (int[] point : points) {
            pq.offer(point);
//            if (pq.size() > K) {
//                pq.poll();
//            }
            //不能用这个，因为我们采取的是最小堆，这样会排出去了。
        }
        for (int i = 0; !pq.isEmpty() && i < K; i++) {
            int[] cur = pq.poll();
            res[i][0] = cur[0];
            res[i][1] = cur[1];
        }
        return res;
    }
}
