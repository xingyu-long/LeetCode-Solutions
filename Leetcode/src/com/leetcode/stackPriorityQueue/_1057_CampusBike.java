package com.leetcode.stackPriorityQueue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class _1057_CampusBike {

    public static class Pair implements Comparable<Pair>{

        int dist, worker, bike;
        public Pair(int dist, int worker, int bike) {
            this.dist = dist;
            this.worker = worker;
            this.bike = bike;
        }
        @Override
        public int compareTo(Pair p) {
            if (this.dist != p.dist) return this.dist - p.dist;
            if (this.worker != p.worker) return this.worker - p.worker;
            return this.bike - p.bike;
        }
    }
    // 利用其距离来分配
    public static int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int i = 0; i < workers.length; i++) {
            int[] worker = workers[i];
            for (int j = 0; j < bikes.length; j++) {
                int[] bike = bikes[j];
                int dist = Math.abs(worker[0] - bike[0]) +
                        Math.abs(worker[1] - bike[1]);
                pq.add(new Pair(dist, i, j));
            }
        }

        int[] res = new int[n];
        Arrays.fill(res, -1);
        Set<Integer> bikeAssigned = new HashSet<>();
        while (bikeAssigned.size() < n && !pq.isEmpty()) {
            Pair workerAndBike = pq.poll();
            if (res[workerAndBike.worker] == -1
                    && ! bikeAssigned.contains(workerAndBike.bike)) {
                res[workerAndBike.worker] = workerAndBike.bike;
                bikeAssigned.add(workerAndBike.bike);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] workers = new int[][]{{0,0},{1,1}, {2, 0}};
        int[][] bikes = {{1,0},{2,2}, {2, 1}};
        int[] res = assignBikes(workers, bikes);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
