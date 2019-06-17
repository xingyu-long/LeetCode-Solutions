package com.leetcode.array;

import java.util.ArrayList;
import java.util.List;

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

public class _57_InsertInterval {
    /**
     * LeetCode No.57 Insert Interval
     * when: 2019/03/13 & 3/14
     * update: 2019/06/17 应该使用更加易懂的方式进行
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
    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (newInterval == null || intervals == null) {
            return intervals;
        }
        List<Interval> res = new ArrayList<Interval>();
        int i = 0;

        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            res.add(intervals.get(i++));
        }
        while (i < intervals.size() && intervals.get(i).start < newInterval.end) {
            newInterval.start = Math.min(intervals.get(i).start, newInterval.start);
            newInterval.end = Math.max(intervals.get(i).end, newInterval.end);
        }
        res.add(newInterval);
        while (i < intervals.size()) {
            res.add(intervals.get(i));
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("1");
        List<Interval> intervals = new ArrayList<>();
        Interval int1 = new Interval(2,6);
        Interval int2 = new Interval(8,10);
        Interval int3 = new Interval(15,18);
        intervals.add(int1);
        intervals.add(int2);
        intervals.add(int3);
        Interval newInterval = new Interval(1,3);
        List<Interval> res = insert(intervals, newInterval);
        System.out.println(res.size());
        for (int i = 0; i < res.size(); i++) {
            System.out.println("Start: "+ res.get(i).start + " End: "+ res.get(i).end);
        }
    }
}


