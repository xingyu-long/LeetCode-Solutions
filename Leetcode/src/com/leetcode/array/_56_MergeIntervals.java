package com.leetcode.array;

import java.util.*;

public class _56_MergeIntervals {
    /**
     * LeetCode No 56. Merge Intervals
     * when: 2019/03/14
     *
     * 思路：都与InsertIntervals 一致 只是从中抽出第一个就行（由于现在的数据是可能内部有overlap而不是像之前没有的情况）
     * 利用所谓的扫描线算法 （第一个的interval处理也很重要） 需要先排序
     * 这里需要先sort！！！(这里sort之后 第一个的start 就一直是第一个输入的start！)
     *
     * 涉及到的数据结构或者方法： 利用new comparator写sort 第一个对象-第二个对象则就是正序
     * @param intervals
     * @return
     */
    // time:O(nlogn) for sorting space: O(n)
    public static int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length < 1) return intervals;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] > o2[0]) return 1;
                else if (o1[0] < o2[0]) return -1;
                else return 0;
            }
        });
        int index = 0;
        int start = intervals[index][0];
        int end  = intervals[index][1];
        int[] newInterval = new int[]{start, end};
        List<int[]> resOfEach = new ArrayList<>();

        // 这里不用比较start是否最小 是因为前面排序了。而57题 插入的newInterval是不确定的，所以需要前后均比较
        for (int[] interval: intervals){
            if (interval[0] <= newInterval[1]){
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            } else {
                resOfEach.add(new int[]{newInterval[0], newInterval[1]});
                newInterval[0] = interval[0];
                newInterval[1] = interval[1];
            }
        }
        // 插入最后一个
        resOfEach.add(newInterval);
        int[][] res = new int[resOfEach.size()][2];
        int i = 0;
        for (int[] each : resOfEach) {
            res[i++] = each;
        }
        return res;
    }
}
