/*
 * @Date: 08/04/2022 13:56:47
 * @LastEditTime: 08/04/2022 14:13:23
 * @Description: monoStack 
 */
package com.leetcode.stack_priority_queue.monoStack;

import java.util.Arrays;
import java.util.Stack;

public class _907_SumofSubarrayMinimums {
    /*
     * 解题思路：
     * 最直接的解法是遍历所有可能的区间，然后找出最小并且加到结果集里，但是这样需要耗费O(n^2)。
     * 另外一种方法则是，不去遍历所有可能的区间，而是想 当前这个数作为最小的情况能够贡献多少次
     * 只需要 nums[i] * count然后相加所有的情况，则就可以得到结果。
     * 这里则需要计算出上一个最小值和下一个最小值
     * 对于这个例子 [1, x, x, x, 5, x, x, x, 2]
     * 当我们遍历到5这个数字的时候 prevSmaller = 0 (value = 1), nextSmaller = 8(value = 2)
     * 则我们形成的区间则是 [{x, {x, {x, {5}, x}, x}, x}]的组合
     * `{` `}` 分别代表了左右可以放置的点 则 4 * 4 = 16 情况
     * 当然这个只是算了value = 5的情况，需要加上其他的情况
     * 
     * 但是我们也需要考虑有重复的最小值的情况：
     * [2, 5, 6, 7, 5, 4, 2]
     * 对于第一个5来说，按照上面的算法，我们可利用的区间是 [5, 6, 7, 5, 4]
     * 当我们看第二个5的时候，发现这个区间也是 [5, 6, 7, 5, 4]
     * 这样的话，就会被重复计算。那我们则需要定义一个规则防止重复计算
     * 对于左边界，我们需要找的是小于或者等于当前这个的情况（同样可以适用于右边界）
     * 这样的话对于第一个5，其区间 [5, 6, 7, 5, 4]
     * 第二个5则是 [6, 7, 5, 4] 这样就防止重复计算
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
