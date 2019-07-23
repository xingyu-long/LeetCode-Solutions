package com.leetcode.string.slidingWindow;

import java.util.Collections;
import java.util.PriorityQueue;

public class _480_SlidingWindowMedian {

    /**
     *  480. Sliding Window Median
     *  When:2019/7/23
     *  Difficulty: Hard
     *  Solution:
     *  利用pq来维护sliding window大小
     * @param nums
     * @param k
     * @return
     */
    //time:O(nk) 因为remove 需要O(k) 然后排序logK 但是 O(k)大 所以time:O(nk) space:O(k)
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) return new double[]{};
        //利用priorityQueue进行
        double[] res = new double[nums.length - k + 1];
        PriorityQueue<Integer> leftMaxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> rightMinHeap = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            // 这里是 <= 也是一种策略，所以在奇数个数的时候也可以保持左边始终大于右边一个
            if (leftMaxHeap.size() <= rightMinHeap.size()) {
                rightMinHeap.add(nums[i]);
                leftMaxHeap.add(rightMinHeap.remove());
            } else {
                leftMaxHeap.add(nums[i]);
                rightMinHeap.add(leftMaxHeap.remove());
            }
            //满足窗口大小的情况
            // k又不一定只是奇数，所以存在中位数是两数之和除以2的情况
            if (leftMaxHeap.size() + rightMinHeap.size() == k) {
                double median;
                if (leftMaxHeap.size() == rightMinHeap.size()) {
                    median = (double)((long)leftMaxHeap.peek() + (long)rightMinHeap.peek()) / 2;
                } else {
                    median = (double) leftMaxHeap.peek();
                }
                int start = i - k + 1;
                res[start] = median;
                if (!leftMaxHeap.remove(nums[start])) {
                    rightMinHeap.remove(nums[start]); // 移除nums[start];
                }
            }
        }
        return res;
    }
}
