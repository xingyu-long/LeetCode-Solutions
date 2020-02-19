package com.leetcode.stackPriorityQueue.monoStack;

import java.util.Stack;

public class FirstLargeNumer {
    // 给定array，针对每个数字找右边第一个比这个数字大的数，没找到填0。例如[2,4,1,5,3]，答案就是[4,5,5,0,0]，
    // 倒序单调栈解决。

    // 利用单调栈来做这个题，当前数比stack的栈顶元素小的话就可能成为candidate，
    // 因为前面可能有更小的（它的右边大一点的就是当前这个数），
    // time:O(n) space:O(n)
    public int[] findRightLargerNumber(int[] nums) {
        Stack<Integer> monoStack = new Stack<>(); // 保持从开始到底部 升序的状态
        int n = nums.length;
        int index = n - 1;
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!monoStack.isEmpty() && nums[i] >= monoStack.peek()) {
                monoStack.pop();
            }
            if (!monoStack.isEmpty()) {
                res[index--] = monoStack.peek();
            } else {
                res[index--] = 0;
            }
            monoStack.push(nums[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        FirstLargeNumer test = new FirstLargeNumer();
        int[] nums = new int[]{1,1,1,1,1}; // 2, 4, 1, 5, 3
        int[] res = test.findRightLargerNumber(nums);
        for (int num : res) {
            System.out.print(num + " ");
        }
    }
}
