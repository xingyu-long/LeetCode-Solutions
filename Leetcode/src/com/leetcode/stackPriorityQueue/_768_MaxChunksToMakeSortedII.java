/*
 * @Date: 07/31/2022 12:12:15
 * @LastEditTime: 07/31/2022 12:34:08
 * @Description: You need to fill out
 */

package com.leetcode.stackPriorityQueue;

import java.util.Arrays;
import java.util.Stack;

public class _768_MaxChunksToMakeSortedII {
    // 利用排序后的数组，进行比较（这里用了和，是因为要杜绝重复值的情况 [1,1,0,0,1]）
    // time: O(nlogn) space: O(1)
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        long sum1 = 0, sum2 = 0;
        int max = 0, res = 0;
        for (int i = 0; i < arr.length; i++) {
            sum1 += arr[i];
            sum2 += sorted[i];
            if (sum1 == sum2) {
                res++;
            }
        }
        return res;
    }

    // time: O(n)
    // space: O(n)
    public int maxChunksToSorted2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        // maxOfLeft[i]: 从左往右，以i结束的坐标的最大值
        int[] maxOfLeft = new int[n];
        // minOfRight[i]: 从右往左，以i结束的坐标的最小值
        int[] minOfRight = new int[n];
        int res = 0;
        maxOfLeft[0] = arr[0];
        for (int i = 1; i < n; i++) {
            maxOfLeft[i] = Math.max(maxOfLeft[i - 1], arr[i]);
        }
        
        minOfRight[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minOfRight[i] = Math.min(minOfRight[i + 1], arr[i]);
        }
        
        for (int i = 0; i < n - 1; i++) {
            // 左边最大的都比右边最小的小，则代表左边这些数可以形成一个chunk
            if (maxOfLeft[i] <= minOfRight[i + 1]) {
                res++;
            }
        }
        return res + 1;
    }

    // time: O(n) space: O(n)
    public int maxChunksToSorted3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            if (stack.isEmpty() || arr[i] >= stack.peek()) {
                stack.push(arr[i]);
            } else {
                int currMax = stack.pop();
                while (!stack.isEmpty() && arr[i] < stack.peek()) {
                    stack.pop();
                }
                // 保持这个chunk最大的在这里，下次新元素进来需要和这个比较
                stack.push(currMax);
            }
        }
        return stack.size();
    }
}
