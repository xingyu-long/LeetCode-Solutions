/*
 * @Date: 08/04/2022 14:21:31
 * @LastEditTime: 08/04/2022 14:22:36
 * @Description: monoStack
 */
package com.leetcode.stack_priority_queue.monoStack;

import java.util.Arrays;
import java.util.Stack;

public class _2104_SumofSubarrayRanges {
    /*
     * 解题思路：
     * 这个题可以作为907的扩展题。主要是可以把区间转换成
     * subarray maximum - subarray minimum (907题)
     * 同样的去重方法
     */
    public long subArrayRanges(int[] arr) {
        // find nextSmaller and prevSmaller
        long res = 0;
        int n = arr.length;
        int[] nextSmaller = new int[n];
        Arrays.fill(nextSmaller, n);
        int[] prevSmallerOrEqual = new int[n];
        Arrays.fill(prevSmallerOrEqual, -1);
        Stack<Integer> stack = new Stack<>();

        // find nextSmaller
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i] < arr[stack.peek()]) {
                nextSmaller[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        stack.clear();

        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] <= arr[stack.peek()]) {
                prevSmallerOrEqual[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        stack.clear();
        // find nextGreater and prevGreater
        int[] nextGreater = new int[n];
        Arrays.fill(nextGreater, n);
        int[] prevGreaterOrEqual = new int[n];
        Arrays.fill(prevGreaterOrEqual, -1);
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[i] > arr[stack.peek()]) {
                nextGreater[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        stack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[i] >= arr[stack.peek()]) {
                prevGreaterOrEqual[stack.peek()] = i;
                stack.pop();
            }
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            long count = (i - prevGreaterOrEqual[i]) * (nextGreater[i] - i);
            res += count * arr[i];
        }

        for (int i = 0; i < n; i++) {
            long count = (i - prevSmallerOrEqual[i]) * (nextSmaller[i] - i);
            res -= count * arr[i];
        }

        return res;
    }
}
