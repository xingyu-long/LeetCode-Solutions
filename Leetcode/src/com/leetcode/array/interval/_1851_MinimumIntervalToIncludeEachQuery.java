package com.leetcode.array.interval;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _1851_MinimumIntervalToIncludeEachQuery {
    class Solution {
        class Data {
            int size;
            int rightIdx;
            
            public Data(int size, int rightIdx) {
                this.size = size;
                this.rightIdx = rightIdx;
            }
        }
        // time: O(nlogn + qlogq)
        // n:  length of intervals
        // q:  length of queries
        public int[] minInterval(int[][] intervals, int[] queries) {
            int[] sortQueries = queries.clone();
            Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));
            Arrays.sort(sortQueries);
            Map<Integer, Integer> map = new HashMap<>(); // query -> size;
            PriorityQueue<Data> pq = new PriorityQueue<>((a, b) -> (a.size - b.size));
            int i = 0, n = intervals.length;
            for (int q : sortQueries) {
                // insert intervals where may contain q
                while (i < n && intervals[i][0] <= q) {
                    int left = intervals[i][0], right = intervals[i][1];
                    pq.offer(new Data(right - left + 1, right));
                    i++;
                }
                
                while (!pq.isEmpty() && pq.peek().rightIdx < q) {
                    // clean invalid intervals
                    pq.poll();
                }
                map.put(q, pq.isEmpty() ? -1 : pq.peek().size);
            }
            
            int[] res = new int[queries.length];
            for (int j = 0; j < queries.length; j++) {
                res[j] = map.get(queries[j]);
            }
            return res;
        }
    }
}
