package com.leetcode.array.interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class _253_MeetingRoomsII {
    /**
     * 253. Meeting Rooms II
     * When: 2019/8/3
     * review1: 11/6/2019
     * Difficulty: Medium
     */
    //  依然是使用start和end来比较
    public int minMeetingRooms2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int res = 0;
        int end = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (starts[i] < ends[end]) {
                res++;
            } else {
                end++;
            }
        }
        return res;
    }

    // 利用heap来做。相当于是有相交的话就要多个房间，然后pq以end排序，就是如果没有相交，就可以按照顺序使用。
    public int minMeetingRooms2_2(int[][] intervals) {
        if (intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // 先用开始的时间做排序
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] interval = pq.poll();
            if (intervals[i][0] >= interval[1]) { // 表明后面那个的开始大于前者的end
                interval[1] = intervals[i][1];
            } else { // 表明有交叉
                pq.offer(intervals[i]);
            }
            pq.offer(interval);
        }
        return pq.size();
    }
}