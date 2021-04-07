/*
 * @Date: 07/04/2020 21:08:27
 * @LastEditTime: 02/28/2021 09:54:37
 * @Description: bucket sorting, customized sort.
 */
package com.leetcode.stackPriorityQueue;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class _895_MaximumFrequencyStack {
    /**
     // priorityQueue, store all the operations.
    public class Node implements Comparable<Node> {
        int val;
        int count;
        int index;

        public Node(int v, int c, int i) {
            val = v;
            count = c;
            index = i;
        }

        @Override
        public int compareTo(Node o) {
            if (this.count != o.count) {
                return o.count - this.count;
            } else {
                return o.index - this.index;
            }
        }
    }

    private PriorityQueue<Node> pq;
    private Map<Integer, Integer> map;
    private int pushIndex;

    public _895_MaximumFrequencyStack() {
        pq = new PriorityQueue<>();
        map = new HashMap<>();
        pushIndex = 0;
    }

    public void push(int x) {
        map.put(x, map.getOrDefault(x, 0) + 1);
        Node node = new Node(x, map.get(x), pushIndex++);
        pq.offer(node);
    }

    public int pop() {
        int res = pq.poll().val;
        map.put(res, map.get(res) - 1);
        return res;
    }
     **/

    // bucket sort, 将出现次数相同的都放在一个里
    // maxFreq只有在当前层没有情况下减一。
    private Map<Integer, Integer> freq;
    private Map<Integer, Stack<Integer>> buckets;
    private int maxFreq;

    public _895_MaximumFrequencyStack() {
        freq = new HashMap<>();
        buckets = new HashMap<>();
        maxFreq = 0;
    }

    public void push(int x) {
        freq.put(x, freq.getOrDefault(x, 0) + 1);
        int count = freq.get(x);
        if (!buckets.containsKey(count)) {
            buckets.put(count, new Stack<>());
        }
        buckets.get(count).add(x);
        maxFreq = Math.max(maxFreq, count);
    }

    public int pop() {
        int res = buckets.get(maxFreq).pop();
        freq.put(res, freq.get(res) - 1);
        if (buckets.get(maxFreq).size() == 0) {
            maxFreq--;
        }
        return res;
    }
}
