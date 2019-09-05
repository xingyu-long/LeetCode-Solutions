package com.leetcode.stackPriorityQueue;

import java.util.PriorityQueue;

public class _215_KthLargestElementinanArray {

    /**
     * 215. Kth Largest Element in an Array
     * When: 2019/06/15
     * review1: 2019/9/4
     * solution:
     * 1. 使用快速选择
     * 2. 利用优先队列
     *
     * @param nums
     * @param k
     * @return
     */
    // time: O(nlogn)
    public int findKthLargest(int[] nums, int k) {
        // 因为快速选择是正序排序，则这里的找第k个最大的数，就可以视为n-k个最小的数
        k = nums.length - k;
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int j = partition(nums, lo, hi);
            if (j < k) {
                lo = j + 1;
            } else if (j > k) {
                hi = j - 1;
            } else {
                break;
            }
        }
        return nums[k];
    }

    public int partition(int[] nums, int lo, int hi) {
        int i = lo, j = hi + 1;
        while (true) {
            while (less(nums[++i], nums[lo])) {
                if (i == hi) break;
            }
            while (less(nums[lo], nums[--j])) {
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(nums, i, j);
        }
        exch(nums, lo, j);
        return j;
    }

    private void exch(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private boolean less(int v, int w) {
        return v < w;
    }

    // 利用最小堆的性质，即能够一直保持首部最小，然后poll就是删除最小的
    // 直到后面得到k个数，并且这个是 n-k, n 范围内 从小到大 所以最后peek()就是其Kth largest number
    // time : O(nlogk) space: O(k)
    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 0, 1, 8, 7, 2, 5, 4, 9, 6};
        int lo = 0;
        int hi = nums.length - 1;
        _215_KthLargestElementinanArray sort = new _215_KthLargestElementinanArray();
        System.out.println(sort.partition(nums, lo, hi));
        for (int num : nums) {
            System.out.print(" " + num);
        }
    }
}
