package com.leetcode.stackPriorityQueue;

import java.util.Iterator;
import java.util.PriorityQueue;

public class _703_KthLargestElementinaStream {
    PriorityQueue<Integer> pq; // 最小堆, 依然使用最小堆保持k个就可以了。
    int count;
    public _703_KthLargestElementinaStream(int k, int[] nums) {
        pq = new PriorityQueue<>();
        count = k;
        for (int num : nums) {
            pq.offer(num);
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > count) {
            pq.poll();
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4,5,8,2};
        int k = 3;
        _703_KthLargestElementinaStream kthLargest = new _703_KthLargestElementinaStream(3, nums);
        System.out.println(kthLargest.add(3));
    }
}
