package com;

import java.util.ArrayList;
import java.util.List;

public class RangeList {

    public class Range {
        int start;
        int end;
        
        public Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public String toString() {
            return "[" + this.start + ", "+  this.end + ")";
        }
    }

    List<Range> list;

    public RangeList() {
        list = new ArrayList<>();
    }

    // time: O(n), space: O(n)
    public void add(int[] range) {
        List<Range> newList = new ArrayList<>();
        int index = 0;
        // go through ranges which end is smaller than current range.
        int start = range[0], end = range[1];
        for (; index < list.size(); index++) {
            Range curr = list.get(index);
            if (curr.end < start) {
                newList.add(new Range(curr.start, curr.end));
            } else {
                break;
            }
        }

        // intersection part
        for (; index < list.size(); index++) {
            Range curr = list.get(index);
            if (end >= curr.start) {
                start = Math.min(start, curr.start);
                end = Math.max(end, curr.end);
            } else {
                break;
            }
        }

        newList.add(new Range(start, end));

        for (; index < list.size(); index++) {
            newList.add(list.get(index));
        }
        list = newList;
    }

    // time: O(n), space: O(n)
    public void remove(int[] range) {
        List<Range> newList = new ArrayList<>();
        int index = 0;
        int start = range[0], end = range[1];
        for (; index < list.size(); index++) {
            Range curr = list.get(index);
            if (curr.end < start) {
                newList.add(new Range(curr.start, curr.end));
            } else {
                break;
            }
        }

        // intersection part
        for (; index < list.size(); index++) {
            Range curr = list.get(index);
            if (end >= curr.start) {
                if (curr.start < start) {
                    newList.add(new Range(curr.start, start));
                } 
                if (curr.end > end) {
                    newList.add(new Range(end, curr.end));
                }
            } else {
                break;
            }
        }

        for (; index < list.size(); index++) {
            newList.add(list.get(index));
        }

        list = newList;
    }

    public void print() {
        System.out.println(list);
    }


    public static void main(String[] args) {
        RangeList rlist = new RangeList();
        rlist.add(new int[]{1, 5});
        rlist.print();
        rlist.add(new int[]{10, 20});
        rlist.print();
        rlist.add(new int[]{20, 20});
        rlist.print();
        rlist.add(new int[]{20, 21});
        rlist.print();
        rlist.add(new int[]{2, 4});
        rlist.print();
        rlist.add(new int[]{3, 8});
        rlist.print();
        rlist.remove(new int[]{10, 10});
        rlist.print();
        rlist.remove(new int[]{10, 11});
        rlist.print();
        rlist.remove(new int[]{15, 17});
        rlist.print();
        rlist.remove(new int[]{3, 19});
        rlist.print();
    }
}
