/*
 * @Date: 06/26/2022 21:57:09
 * @LastEditTime: 06/26/2022 21:57:09
 * @Description: You need to fill out
 */

package com.leetcode.array.interval;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class _759_EmployeeFreeTime {
    class Interval {
        public int start;
        public int end;
    
        public Interval() {}
    
        public Interval(int _start, int _end) {
            start = _start;
            end = _end;
        }
    }
    
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        if (schedule == null || schedule.size() == 0) {
            return new ArrayList<>();
        }
        
        PriorityQueue<Interval> pq = new PriorityQueue<>((a, b) -> (a.start - b.start));
        for (List<Interval> person : schedule) {
            for (Interval interval : person) {
                pq.offer(interval);
            }
        }
        List<Interval> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            Interval curr = pq.poll();
            Interval next = pq.peek();
            // no more left
            if (next == null) break;
            if (curr.end >= next.start) {
                // connect
                curr.end = Math.max(curr.end, next.end);
                pq.poll(); // remove next;
                pq.offer(curr);
            } else {
                res.add(new Interval(curr.end, next.start));
            }
        }
        return res;
    }
}

