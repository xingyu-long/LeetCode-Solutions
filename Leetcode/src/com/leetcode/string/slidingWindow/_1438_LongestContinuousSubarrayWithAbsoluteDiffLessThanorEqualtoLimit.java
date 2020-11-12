package com.leetcode.string.slidingWindow;

import java.util.Deque;
import java.util.LinkedList;
import java.util.TreeMap;

public class _1438_LongestContinuousSubarrayWithAbsoluteDiffLessThanorEqualtoLimit {
    // 利用sliding window 但是如何高效的获取最小最大值，这个比较重要。
    // 利用treemap自带的firstKey(), lastKey()来做
    // time: O(NlogN)
    public int longestSubarray(int[] nums, int limit) {
        // 用treemap
        if (nums == null || nums.length == 0) {
            return 0;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        int res = 0;
        int start = 0, end = 0;
        int n = nums.length;
        while (end < n) {
            map.put(nums[end], map.getOrDefault(nums[end], 0) + 1);
            while (map.lastKey() - map.firstKey() > limit) {
                map.put(nums[start], map.get(nums[start]) - 1);
                if (map.get(nums[start]) == 0) {
                    map.remove(nums[start]);
                }
                start++;
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }

    // Time: O(n) 用两个deque
    public int longestSubarray2(int[] nums, int limit) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int res = 0;
        Deque<Integer> min = new LinkedList<>();
        Deque<Integer> max = new LinkedList<>();
        int start = 0;
        for (int i = 0; i < n; i++) {
            while (!min.isEmpty() && nums[i] < nums[min.peekLast()]) {
                min.pollLast();
            }
            min.addLast(i);

            while (!max.isEmpty() && nums[i] > nums[max.peekLast()]) {
                max.pollLast();
            }
            max.addLast(i);

            while (nums[max.peekFirst()] - nums[min.peekFirst()] > limit) {
                if (nums[max.peekFirst()] == nums[start]) max.pollFirst();
                if (nums[min.peekFirst()] == nums[start]) min.pollFirst();
                start++;
            }
            res = Math.max(res, i - start + 1);
        }

        return res;
    }
}
