package com.leetcode.stack_priority_queue;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class _950_RevealCardsInIncreasingOrder {
    // 利用deque存储其该放置的index。
    // 主要是要看懂题目
    // time:O(nlogn) space:O(n)
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int[] res = new int[n];
        Arrays.sort(deck);
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) q.offer(i);

        for (int card : deck) {
            // reveal it.
            res[q.pollFirst()] = card;
            // placed in bottom.
            if (!q.isEmpty()) q.offer(q.pollFirst());
        }
        return res;
    }
}
