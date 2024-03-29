package com.leetcode.stack_priority_queue.monoStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @Date: 05/19/2020, 10/26/2020
 * @Description: Mono Stack
 **/
public class _901_OnlineStockSpan {

    // 相当于维护一个单调递减的栈，并且这里应该是pair，方便书写所以写的两个栈同步。一个记录price，一个记录最长的连续长度。
    Stack<Integer> stack;
    Stack<Integer> count;

    public _901_OnlineStockSpan() {
        stack = new Stack<>();
        count = new Stack<>();
    }

    // amortized O(1)
    public int next(int price) {
        int span = 1;
        while (!stack.isEmpty() && price >= stack.peek()) {
            stack.pop();
            span += count.pop();
        }
        stack.push(price);
        count.push(span);
        return span;
    }
}

// 利用map来记录对应的关系
class StockSpanner2 {

    Map<Integer, Integer> map;
    Stack<Integer> stack;
    
    public StockSpanner2() {
        map = new HashMap<>();
        stack = new Stack<>();
    }
    
    public int next(int price) {
        int res = 1;
        if (!stack.isEmpty() && price >= stack.peek()) {
            while (!stack.isEmpty() && price >= stack.peek()) {
                res += map.get(stack.peek());
                map.remove(stack.peek());
                stack.pop();
            }
            stack.push(price);
            map.put(price, res);
            return res;
        } else {
            stack.push(price);
            map.put(price, res);
            return res;
        }
    }
}