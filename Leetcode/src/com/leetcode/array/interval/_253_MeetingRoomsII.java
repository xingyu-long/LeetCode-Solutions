package com.leetcode.array.interval;

import java.util.Arrays;
import java.util.Comparator;

public class _253_MeetingRoomsII {
    /**
     *  253. Meeting Rooms II
     *  When: 2019/8/3
     *  Difficulty: Medium
     *  其实用以前的方法应该就可以，先按照start排序然后重叠的部分+1
     */

    // 自己的解法，没有跑数据
    // time:O(nlogN) space:O(1)
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        int res = 1;//肯定存在至少一个
        for (int i = 1; i <  intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                res++;
            }
        }
        return res;
    }

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
}