package com.leetcode.array.interval;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

// https://leetcode.com/problems/my-calendar-i/discuss/109475/JavaC%2B%2B-Clean-Code-with-Explanation
public class _729_MyCalendarI {
    public static class Interval {
        int start;
        int end;

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    List<Interval> intervals;
    TreeSet<Interval> treeSet;
    public _729_MyCalendarI() {
        treeSet = new TreeSet<>((a, b) -> a.start - b.start);
        intervals = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        if (intervals.isEmpty()) {
            intervals.add(new Interval(start, end));
            return true;
        } else {
            // 先遍历全部。这个包括了所有的交叉的情况
            for (Interval interval : intervals) {
                int maxS = Math.max(interval.start, start);
                int minE = Math.min(interval.end, end);
                if (maxS < minE) {
                    return false;
                }
            }
            // 表明ok。
            intervals.add(new Interval(start, end));
            return true;
        }
    }
    // 利用treeSet相当于是可以找到当前的左右两边，然后start和左边的end比较，小于则有交叉，
    // end与右边的start比较，大于则有交叉。
    public boolean book2(int start, int end) {
        Interval interval = new Interval(start, end);
        Interval floor = treeSet.floor(interval);
        Interval ceil = treeSet.ceiling(interval);
        if (floor != null && start < floor.end) return false;
        if (ceil != null && end > ceil.start) return false;
        treeSet.add(interval);
        return true;
    }

    public static void main(String[] args) {
        TreeSet<Interval> treeSet = new TreeSet<>((a, b) -> a.start - b.start);
        treeSet.add(new Interval(10, 20));
        treeSet.add(new Interval(25, 30));
        treeSet.add(new Interval(35, 40));
        Interval interval = new Interval(31, 32);
        System.out.println("floor = " + treeSet.floor(interval).start + " floor end = " + treeSet.floor(interval).end);
    }
}
