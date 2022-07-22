/*
 * @Date: 07/20/2022 17:41:58
 * @LastEditTime: 07/21/2022 10:18:21
 * @Description: Stack, Doubly LinkedList
 */
package com.leetcode.stackPriorityQueue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeMap;

public class _716_MaxStack {
    public class DNode {
        public DNode prev, next;
        public int val;
    }
    
    TreeMap<Integer, Deque<DNode>> map;
    DNode head, tail;
    
    public _716_MaxStack() {
        map = new TreeMap<>();
        
        // init head and tail
        head = new DNode();
        head.prev = null;
        tail = new DNode();
        tail.next = null;
        
        head.next = tail;
        tail.prev = head;
    }
    
    // time: O(logN) for treemap
    public void push(int x) {
        DNode node = new DNode();
        node.val = x;
        
        DNode last = tail.prev;
        // insert node after last
        last.next = node;
        node.prev = last;
        
        node.next = tail;
        tail.prev = node;
        
        map.putIfAbsent(x, new LinkedList<>());
        map.get(x).add(node);
    }
    
    // time: O(logN)
    public int pop() {
        DNode last = tail.prev;
        if (last == head) {
            return 0;
        }
        removeNode(last);
        map.get(last.val).pollLast();
        if (map.get(last.val).isEmpty()) {
            map.remove(last.val);
        }
        return last.val;
    }
    
    // time: O(1)
    public int top() {
        DNode last = tail.prev;
        return last.val;
    }
    
    // O(1)
    public int peekMax() {
        return map.lastKey();
    }
    
    // O(logN)
    public int popMax() {
        int max = map.lastKey();
        DNode last = map.get(max).pollLast();
        removeNode(last);
        if (map.get(max).isEmpty()) {
            map.remove(max);
        }
        return max;
    }
    
    private void removeNode(DNode node) {
        DNode savedPrev = node.prev;
        DNode savedNext = node.next;
        
        savedPrev.next = savedNext;
        savedNext.prev = savedPrev;
    }
}
