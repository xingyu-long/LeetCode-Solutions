package com.leetcode.stackPriorityQueue.monoQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class _239_SlidingWindowMaximum {

    /**
     * 239. Sliding Window Maximum
     * when: 2019/03/26
     * review1:10/22/2019
     * <p>
     * 涉及到的数据结构：
     * deque
     * @param nums
     * @param k
     * @return
     */

    public static int[] maxSlidingWindow(int[] nums, int k) {
        //solution2: deque Time: O(n) Space: O(n)
        // deque 存的是index 并且从大到小排列
        // https://www.youtube.com/watch?v=J6o_Wz-UGvc
        // corner cases.
        if (nums == null || nums.length == 0 || k == 0) return new int[]{};

        // store index (nums[index] in descending order)
        Deque<Integer> deque = new LinkedList<>();
        // result array
        int[] res = new int[nums.length - k + 1];
        // int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // check if it out of bound (should be in  [i - (k-1), i] eg: i = 3, left:1)
            if (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                deque.pollFirst();
            }
            // pop it if this value is greater than the before.
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offer(i);
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peek()];
            }
        }
        return res;
    }

    //time: O( (n-k+1) * k) 当k= n/2 则是 n^2 space: O(1)
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums.length < 1 || nums == null) return new int[0];
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length - k + 1; i++) {
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            res.add(max);
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            result[i] = res.get(i);
        }
        return result;
    }


    // 可能求sliding window里min然后求最大
    public int maxSlidingWindow3(int[] nums, int k) {
        // corner cases.
        if (nums == null || nums.length == 0 || k == 0) return Integer.MAX_VALUE;

        // store index (nums[index] in descending order)
        Deque<Integer> deque = new LinkedList<>();
        // result array
        int[] min = new int[nums.length - k + 1];
        // int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // check if it out of bound (should be in  [i - (k-1), i] eg: i = 3, left:1)
            while (!deque.isEmpty() && deque.peek() < i - k + 1) {
                deque.poll();
            }
            // pop it if this value is less than the before.
            while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                deque.pollLast();
            }

            deque.offer(i);
            if (i >= k - 1) {
                min[i - k + 1] = nums[deque.peek()];
            }
        }
        // get max
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length - k + 1; i++) {
            max = Math.max(max, min[i]);
        }
        System.out.println(max);
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        maxSlidingWindow(nums, k);
    }
}
