package com.leetcode.string.sliding_window;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * @Date: 2019/7/23, 05/29/2020
 * @Description: PQ
 **/
public class _480_SlidingWindowMedian {

    // 记得注意考虑两数求平均数的时候，转换为long
    //time:O(nk) 因为remove 需要O(k) 然后排序logK 但是 O(k)大 所以time:O(nk) space:O(k)
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new double[]{};
        }
        //利用priorityQueue进行
        double[] res = new double[nums.length - k + 1];
        PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> min = new PriorityQueue<>();

        for (int i = 0; i < nums.length; i++) {
            // 这里是 <= 也是一种策略，所以在奇数个数的时候也可以保持左边始终大于右边一个
            if (max.size() <= min.size()) {
                min.add(nums[i]);
                max.add(min.remove());
            } else {
                max.add(nums[i]);
                min.add(max.remove());
            }
            //满足窗口大小的情况
            // k又不一定只是奇数，所以存在中位数是两数之和除以2的情况
            if (max.size() + min.size() == k) {
                double median;
                if (max.size() == min.size()) {
                    // 需要考虑到相加的时候可能会溢出。
                    median = (double) ((long) max.peek() + (long) min.peek()) / 2;
                } else {
                    median = (double) max.peek();
                }
                int start = i - k + 1;
                res[start] = median;
                if (!max.remove(nums[start])) {
                    min.remove(nums[start]); // 移除nums[start];
                }
            }
        }
        return res;
    }

    public static double[] medianSlidingWindow2(int[] nums, int k) {
        // even for k ??? 利用两个PQ。
        if (nums == null || nums.length == 0) {
            return new double[]{};
        }
        double[] res = new double[nums.length - k + 1];
        PriorityQueue<Integer> max = new PriorityQueue<>(
            (a, b) -> b.compareTo(a));
        PriorityQueue<Integer> min = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            min.offer(nums[i]);
            max.offer(min.poll());
            if (max.size() > min.size()) {
                min.offer(max.poll()); // 保证minHeap多些。
            }

            if (min.size() + max.size() == k) {
                double median;
                if (min.size() == max.size()) {
                    median = (double) ((long) min.peek() + (long) max.peek()) / 2.0;
                } else {
                    median = (double) (min.peek());
                }
                res[i - k + 1] = median;
                if (!min.remove(nums[i - k + 1])) {
                    max.remove(nums[i - k + 1]);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {Integer.MAX_VALUE, Integer.MAX_VALUE};
        int k = 2;
        System.out.println(medianSlidingWindow2(nums, k).toString());
    }
}
