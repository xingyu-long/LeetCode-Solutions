package com.leetcode.array.interval;

import java.util.*;

public class _56_MergeIntervals {
    /**
     * LeetCode No 56. Merge Intervals
     * when: 2019/03/14
     * review1:11/4/2019
     * 注意end的比较。
     * 思路：都与InsertIntervals 一致 只是从中抽出第一个就行（由于现在的数据是可能内部有overlap而不是像之前没有的情况）
     * 利用所谓的扫描线算法 （第一个的interval处理也很重要） 需要先排序
     * 这里需要先sort！！！(这里sort之后 第一个的start 就一直是第一个输入的start！)
     * <p>
     * 涉及到的数据结构或者方法： 利用new comparator写sort 第一个对象-第二个对象则就是正序
     *
     * @param intervals
     * @return
     */
    // 可能有的follow up 第二题是follow up。如果这些区间不是一起给你，是一个一个给，
    // 然后中间会让你输出merge之后的结果。我一开始想的是每次有新的进来进行merge。
    // 他就提示我可以先把所有的interval存起来，不用merge，然后需要输出结果的时候再去merge。
    // 然后问怎么样能够比较好的存这些interval。我想了一下可以用BST，把左端点作为key。
    // 后来经过他的引导，我想到可以用inorder traverse把BST过一遍，然后得到排好序的区间，
    // 然后用第一道题的方法做。这道题基本上是引导着做出来的。
    // time:O(nlogn) for sorting space: O(n)
    public int[][] merge(int[][] intervals) {
        // 利用后一个的start与前一个的end作比较
        if (intervals == null || intervals.length == 0) return new int[][]{};
        List<List<Integer>> list = new ArrayList<>();
        // needs to sort first.
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int start = intervals[0][0];
        int end = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= end) {
                end = Math.max(end, intervals[i][1]);
            } else {
                list.add(new ArrayList<>(Arrays.asList(start, end)));
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        // add the last pair.
        list.add(new ArrayList<>(Arrays.asList(start, end)));
        int[][] res = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            res[i][0] = list.get(i).get(0);
            res[i][1] = list.get(i).get(1);
        }
        return res;
    }
}
