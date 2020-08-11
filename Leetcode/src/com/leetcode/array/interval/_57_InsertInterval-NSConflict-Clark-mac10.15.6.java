package com.leetcode.array.interval;

import java.util.ArrayList;
import java.util.List;

public class _57_InsertInterval {
    /**
     *  No.57 Insert Interval
     *  when: 2019/03/13 & 3/14, 2019/7/13, 2019/06/17
     *  04/09/2020
     *
     * 解题思路：
     * 首先一个循环表示以前的每一个数组
     * 分三种情况：
     *  1. 原数组的end < 新数组的start 直接插入res
     *  2. 原数组的start <= 新数组的end 则是比较两个数组的start 最小作为首 两个数组的end 最大作为尾
     *  3. 原数组的start > 新数组的end 直接插入res
     *  这里的含义是数组 但是可以理解为就是interval对象 然后有start以及end 属性
     *  这里之前有while的方法容易超时，内存不够（应该是进入死循环）
     *  利用for循环来遍历Interval 然后分上面的三种情况进行处理。
     *
     *  涉及到的数据结构或者方法： ArrayList<>()
     * @param intervals
     * @param newInterval
     * @return
     */
    // time: O(n) space:O(n)
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (newInterval.length == 0) {
            return intervals;
        }
        List<int[]> resOfEach = new ArrayList<>();
        int index = 0;

        // 表明新数组的开头都大于前面的结尾，则一直添加原来的
        while (index < intervals.length && intervals[index][1] < newInterval[0]) {
            resOfEach.add(intervals[index]);
            index++;
        }

        // 表明各种能够交叉的情况,并且考虑重合的情况 所以要 <=
        while (index < intervals.length && intervals[index][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[index][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[index][1], newInterval[1]);
            index++;
        }
        resOfEach.add(newInterval);

        // 表明新的数组与原来的没有交集，所以最后加入剩下的即可
        while (index < intervals.length) {
            resOfEach.add(intervals[index++]);
        }

        int[][] res = new int[resOfEach.size()][2];
        int i = 0;
        for (int[] each : resOfEach) {
            res[i++] = each;
        }
        return res;
    }
}


