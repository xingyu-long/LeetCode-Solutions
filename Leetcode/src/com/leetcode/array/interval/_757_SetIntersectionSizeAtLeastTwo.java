package com.leetcode.array.interval;

import java.util.Arrays;
import java.util.TreeSet;

/**
 * @Date: 08/01/2020
 * @Description: Greedy
 **/
public class _757_SetIntersectionSizeAtLeastTwo {

    // 保持最大的两个数，然后去和当前区间比较看是否有重叠。
    public int intersectionSizeTwo(int[][] intervals) {
        int res = 0;
        if (intervals == null || intervals.length == 0 ||
            intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }
        Arrays.sort(intervals, (a, b) -> (a[1] != b[1] ? a[1] - b[1] : b[0] - a[0]));
        int left = intervals[0][1] - 1;
        int right = intervals[0][1];
        res += 2;
        for (int i = 1; i < intervals.length; i++) {
            int[] curr = intervals[i];
            if (left < curr[0] && curr[0] <= right) {
                res++;
                left = right;
                right = curr[1];
            } else if (curr[0] > right) {
                res += 2;
                left = curr[1] - 1;
                right = curr[1];
            }
        }
        return res;
    }

    public static int intersectionSizeTwo2(int[][] intervals) {
        if (intervals == null || intervals.length == 0 ||
            intervals[0] == null || intervals[0].length == 0) {
            return 0;
        }
        TreeSet<Integer> treeset = new TreeSet<>((a, b) -> a.compareTo(b));
        for (int[] interval : intervals) {
            Integer lower = treeset.floor(interval[1]);
            Integer upper = treeset.ceiling(interval[0]);
            if (lower == null || upper == null) { // 没有重叠的情况，就算有一个不是null但是值也很小
                treeset.add(interval[1] - 1);
                treeset.add(interval[1]);
            } else if (lower.equals(upper)) { // 是有一个重叠的情况
                if (!treeset.add(interval[1])) treeset.add(interval[1] - 1);
            }
        }
        return treeset.size();
    }

    public static void main(String[] args) {
        int[][] intervals = {{1,2}, {2,3}, {2,4}, {4,5}};
        System.out.print(intersectionSizeTwo2(intervals));
    }

}
