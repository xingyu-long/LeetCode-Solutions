/*
 * @Date: 06/23/2022 21:08:55
 * @LastEditTime: 06/23/2022 21:14:31
 * @Description: You need to fill out
 */
package com;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class StockSpanner {

    Map<Integer, Integer> map;
    Stack<Integer> stack;
    
    public StockSpanner() {
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

    public static void main(String[] args) {
        StockSpanner spanner = new StockSpanner();
        spanner.next(100);
        spanner.next(80);
        spanner.next(60);
        spanner.next(70);
        spanner.next(60);
        spanner.next(75);
        spanner.next(85);
    }
}

