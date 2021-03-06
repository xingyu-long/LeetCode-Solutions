package com.leetcode.greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class _1353_MaximumNumberofEventsThatCanBeAttended {
    // 主要的思想是越早结束的越早处理。
    // time:O(n^2) space:O(~1)
    public int maxEvents(int[][] events) {
        if (events == null || events.length == 0 || events[0] == null || events.length == 0) {
            return 0;
        }
        Arrays.sort(events, (a, b) -> (a[1] - b[1] != 0 ? a[1] - b[1] : a[0] - b[0]));
        int res = 0;
        boolean[] attend = new boolean[100001];
        for (int[] event : events) {
            for (int i = event[0]; i <= event[1]; i++) {
                if (!attend[i]) { // 当前还没访问，我需要占这个位置
                    attend[i] = true;
                    res++;
                    break;
                }
            }
        }
        return res;
    }

    // time:O(nlogn) 表示也是开始最早结束时间最早的开始处理。
    public int maxEvents2(int[][] events) {
        if (events == null || events.length == 0 || events[0] == null || events.length == 0) {
            return 0;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));
        int index = 0, res = 0, n = events.length;
        for (int d = 1; d <= 100000; d++) {
            while (index < n && events[index][0] == d)
                pq.offer(events[index++][1]); // 加入这个的结束时间。
            while (pq.size() > 0 && pq.peek() < d)
                pq.poll(); // 结束时间小于当前的d，就说明之前已经处理过才对。这是已经结束的event
            if (pq.size() > 0) {
                res++; // 表示用d这天去参加 然后把那个end取出来。
                pq.poll();
            }
        }
        return res;
    }
}
