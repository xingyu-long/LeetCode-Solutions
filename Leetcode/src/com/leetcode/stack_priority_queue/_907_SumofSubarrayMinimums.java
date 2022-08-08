/*
 * @Date: 08/04/2022 13:56:47
 * @LastEditTime: 08/04/2022 14:00:06
 * @Description: monotic 
 */
package com.leetcode.stack_priority_queue;

import java.util.Arrays;
import java.util.Stack;

public class _907_SumofSubarrayMinimums {
    /*
     * 解题思路：
     * 最直接的解法是遍历所有可能的区间，然后找出最小并且加到结果集里，但是这样需要耗费O(n^2)。
     * 另外一种方法则是，不去遍历所有可能的区间，而是想 当前这个数作为最小的情况能够贡献多少次
     * 只需要nums[i] * count然后相加所有的情况，则就可以得到结果。
     * 
     */
    public int sumSubarrayMins(int[] arr) {
        int MOD = (int) Math.pow(10, 9) + 7;
        // find nextSmaller and prevSmaller
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

        long res = 0;
        for (int i = 0; i < n; i++) {
            // 计算个数（这里可能会溢出，所以需要用MOD）
            long count = (i - prevSmallerOrEqual[i]) * (nextSmaller[i] - i) % MOD;
            res = (res + arr[i] * count) % MOD;
        }
        return (int) res;
    }
}
