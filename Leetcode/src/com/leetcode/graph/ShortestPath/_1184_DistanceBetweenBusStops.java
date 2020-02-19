package com.leetcode.graph.ShortestPath;

public class _1184_DistanceBetweenBusStops {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        // time:O(n) space:O(1)
        // 咋一看应该是graph search的问题。这个不用search，直接看两边走过去的最小值。
        if (distance == null || distance.length == 0) return 0;
        int oneWay = 0;
        int anotherWay = 0;
        int n = distance.length;
        int i = start, j = destination;
        if (start > destination) {
            i = destination;
            j = start;
        }
        for (int k = 0; k < n; k++) {
            if (k == i && k < j) oneWay += distance[i++];
            else anotherWay += distance[k];
        }
        return Math.min(oneWay, anotherWay);
    }
}
