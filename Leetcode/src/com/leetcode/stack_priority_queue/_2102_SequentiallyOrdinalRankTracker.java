/*
 * @Date: 08/13/2022 22:16:27
 * @LastEditTime: 08/13/2022 22:18:01
 * @Description: Priority Queue
 */
package com.leetcode.stack_priority_queue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _2102_SequentiallyOrdinalRankTracker {
    class Node implements Comparable<Node>{
        String name;
        int score;
        
        public Node(String name, int score) {
            this.score = score;
            this.name = name;
        }
        @Override
        public int compareTo(Node o) {
            if (this.score == o.score) {
                return this.name.compareTo(o.name);
            }
            return o.score - this.score;
        } 
    }
    
    PriorityQueue<Node> minPQ;
    PriorityQueue<Node> maxPQ;
    
    public _2102_SequentiallyOrdinalRankTracker() {
        maxPQ = new PriorityQueue<>();
        minPQ = new PriorityQueue<>(Comparator.reverseOrder());
    }
    
    // 始终保持第n小的数据在maxPQ的第一个
    public void add(String name, int score) {
        Node node = new Node(name, score);
        minPQ.offer(node);
        maxPQ.offer(minPQ.poll());
    }
    
    public String get() {
        Node node = maxPQ.poll();
        minPQ.offer(node);
        return node.name;
    }
}
