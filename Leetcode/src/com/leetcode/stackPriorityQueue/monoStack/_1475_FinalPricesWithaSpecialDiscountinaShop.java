package com.leetcode.stackPriorityQueue.monoStack;

import java.util.Stack;

/**
 * @Date: 06/13/2020
 * @Description: mono stack
 **/
public class _1475_FinalPricesWithaSpecialDiscountinaShop {
    // 不用map存关系也可以
    // time:O(n) space:O(n)
    public int[] finalPrices(int[] prices) {
        if (prices == null || prices.length == 0) {
            return new int[]{};
        }
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[prices.length];
        for (int i = 0; i < prices.length; i++) {
            res[i] = prices[i];
            while (!stack.isEmpty() && prices[i] <= prices[stack.peek()]) {
                res[stack.pop()] -= prices[i];
            }
            stack.push(i);
        }
        return res;
    }
}
