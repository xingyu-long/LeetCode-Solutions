package com.leetcode.greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @Date: 05/14/2020
 * @Description: Greedy,
 **/
public class _621_TaskScheduler {
    // time:O(nlogn)
    public int leastInterval(char[] tasks, int n) {
        int[] count = new int[26];
        for (char task : tasks) {
            count[task - 'A']++;
        }
        Arrays.sort(count);
        int i = 25, max = count[25], len = tasks.length;
        while (i >= 0 && count[i] == max) {
            i--;
        }
        // 有max - 1部分，每个部分有n + 1个字符（不够的由idle凑满） 25-i表示最后需要加的一部分
        return Math.max(len, (max - 1) * (n + 1) + (25 - i));
    }
    

    /*
     * Solution:
     * 首先确认的是，为了减少idle的出现，应该优先的用最多的字符（利用priority queue）来追踪
     * 另外的话，对于每次使用的字符，需要设置一个冷冻时间，到了这个时间，则可以被加入到pq里面去
     * 
     * time: O(nlogn)
     */
    public int leastInterval2(char[] tasks, int n) {
        int[] count = new int[26];
        for (int t : tasks) {
            count[t - 'A']++;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int c : count) {
            if (c > 0) {
                pq.offer(c);
            }
        }
        // time -> # of rest char
        Map<Integer, Integer> trackMap = new HashMap<>();
        int curr = 0;
        while (!pq.isEmpty() || !trackMap.isEmpty()) {
            if (!pq.isEmpty()) {
                int c = pq.poll();
                if (--c > 0) {
                    // more count needs to be placed later
                    trackMap.put(curr + n, c);
                }
            }
            
            // release when time is right
            if (trackMap.containsKey(curr)) {
                pq.offer(trackMap.remove(curr));
            }
            curr++;
        }
        return curr;
    }
}
