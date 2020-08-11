package com.leetcode.stackPriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _295_FindMedianFromDataStream {

    /**
     *  295. Find Median From Data Stream
     *  When:2019/7/12
     *  review1:11/17/2019
     *  Difficulty: Hard
     *  Solution:
     *    two heaps
     */
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    /** initialize your data structure here. */
    public _295_FindMedianFromDataStream() {
        minHeap = new PriorityQueue<>(); //放置大的一半
        maxHeap = new PriorityQueue<>(new Comparator<Integer>() { // 放置小的那一半
            @Override
            public int compare(Integer o1, Integer o2) {
                if (o1 > o2) return -1;
                else if (o1 < o2) return 1;
                else return 0;
            }
        });
    }

    // 最开始会有一个初始化问题，需要插入！
    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.add(num);
            return;
        }

        if (maxHeap.size() == minHeap.size()) {
            if (num < maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
        } else if (maxHeap.size() > minHeap.size()) {
            if (num > maxHeap.peek()) {
                minHeap.add(num);
            } else {
                // 表示比最大堆的第一个元素小，所以应该在最大堆里面，但是由于这里本身元素就比最小堆多
                // 所以需要先把 maxHeap 的顶部弹出，放到 miniHeap，然后 num 加入到 maxHeap
                minHeap.add(maxHeap.poll());
                maxHeap.add(num);
            }
        } else { // maxHeap.size() < minHeap.size()
            if (num < minHeap.peek()) {
                maxHeap.add(num);
            } else {
                maxHeap.add(minHeap.poll());
                minHeap.add(num);
            }
        }
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (double)(maxHeap.peek() + minHeap.peek()) / 2;
        } else if (minHeap.size() > maxHeap.size()) {
            return minHeap.peek();
        } else {
            return maxHeap.peek();
        }
    }

    public static void main(String[] args) {
        _295_FindMedianFromDataStream find = new _295_FindMedianFromDataStream();
        find.addNum(1);
        find.addNum(2);
        System.out.println(find.findMedian());
    }
}

// 这个看起来更加的简便，当然上面的讨论情况也是很有意义的。
class MedianFinder2 {

    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    /** initialize your data structure here. */
    public MedianFinder2() {

        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
    }

    public void addNum(int num) {
        minHeap.offer(num);
        maxHeap.offer(minHeap.poll());
        if (minHeap.size() < maxHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian() {
        if (minHeap.size() == maxHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            return (double) minHeap.peek();
        }
    }
}
