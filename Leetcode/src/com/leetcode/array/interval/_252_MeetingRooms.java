package com.leetcode.array.interval;

import java.util.Arrays;
import java.util.Comparator;

public class _252_MeetingRooms {
    /**
     *  252. Meeting Rooms
     *  When: 2019/8/3
     *  Difficulty: Easy
     *  先按照start 排序，然后开是否有重叠的，重叠就不可以都参加


     compareTo()
     if s1 > s2, it returns positive number
     if s1 < s2, it returns negative number
     if s1 == s2, it returns 0
     * @param intervals
     * @return
     */
    //time:O(nlogN) space:O(1)
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] > o2[0]) return 1;
                else if (o1[0] < o2[0]) return -1;
                else return 0;
            }
        });

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}