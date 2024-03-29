package com.leetcode.string.sliding_window;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 2019/7/22, 2019/8/29, 06/02/2020
 * @Description: Sliding Window, DP
 **/
public class _487_MaxConsecutiveOnesII {

    public int findMaxConsecutiveOnes(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int res = 0;
        int zeroCount = 0;
        int start = 0, end = 0;
        int K = 1;
        while (end < A.length) {
            if (A[end] == 0) {
                zeroCount++;
            }
            while (zeroCount > K) {
                if (A[start] == 0) {
                    zeroCount--;
                }
                start++;//移动窗口
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }
    // 时间前后DP
    public int findMaxConsecutiveOnes2(int[] nums) {
        int n = nums.length;
        int[] flip = new int[n];// 当前flip的最大值
        int[] no = new int[n];// 当前不flip的最大值
        flip[0] = (nums[0] == 0 ? 1 : 0);
        no[0] = (nums[0] == 1 ? 1 : 0);
        int res = Math.max(flip[0], no[0]);
        for (int i = 1; i < n; i++) {
            if (nums[i] == 0) {
                flip[i] = no[i - 1] + 1;
                no[i] = 0; // 这里没有考虑到重新开始，这个很重要！
            } else {
                flip[i] = flip[i - 1] + 1; // 表明不用flip，因为是1嘛！
                no[i] = no[i - 1] + 1;
            }
            res = Math.max(res, Math.max(flip[i], no[i]));
        }
        return res;
    }

    // What if the input numbers come in one by one as an infinite stream?
    // In other words, you can't store all numbers coming from the stream as
    // it's too large to hold in memory. Could you solve it efficiently?
    // 利用queue来代替left移动
    public int findMaxConsecutiveOnes3(int[] nums) {
        int start = 0, end = 0;
        int res = 0, n = nums.length;
        int  k = 1;
        Queue<Integer> queue = new LinkedList<>();
        while (end < n) {
            if (nums[end] == 0) {
                queue.offer(end);
            }
            while (queue.size() > k) {
                start = queue.peek() + 1; // 相当于start++的操作
                queue.poll();
            }
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }
}