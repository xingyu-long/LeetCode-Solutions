package com.leetcode.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
    public List<Interval> merge(List<Interval> intervals) {
        if (intervals == null || intervals.size() < 1) return intervals;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start - o2.start;
            }
        });
        int start = intervals.get(0).start;
        int end  = intervals.get(0).end;
        List<Interval> res = new ArrayList<>();
        // 这里不用比较start是否最小 是因为前面排序了。而57题 插入的newInterval是不确定的，所以需要前后均比较
        for (Interval interval: intervals){
            if (interval.start <= end){
                end = Math.max(end, interval.end);
            } else {
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        // 插入最后一个
        res.add(new Interval(start, end));
        return res;
    }
    public static void main(String[] args){

    }
}
