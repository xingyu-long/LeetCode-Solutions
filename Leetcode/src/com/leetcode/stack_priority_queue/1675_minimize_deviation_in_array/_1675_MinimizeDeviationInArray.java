/*
 * @Date: 01/30/2021 09:56:31
 * @LastEditTime: 01/30/2021 10:01:20
 * @Description: PriorityQueue, TreeSet
 */
package com.leetcode.stack_priority_queue;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class _1675_MinimizeDeviationInArray {
    // 分析的步骤很关键: 先把奇数double之后一直除2即可。
    public int minimumDeviation(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int min = Integer.MAX_VALUE;
        for (int num : nums) {
            num = (num % 2 == 0 ? num : num * 2);
            pq.offer(num);
            min = Math.min(min, num);
        }
        int res = pq.peek() - min;
        while (pq.peek() % 2 == 0) {
            int curr = pq.poll();
            pq.offer(curr / 2);
            min = Math.min(min, curr / 2);
            res = Math.min(res, pq.peek() - min);
        }
        return res;
    }

    public int minimumDeviation2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        TreeSet<Integer> ts = new TreeSet<>();
        for (int num : nums) {
            num = (num % 2 == 0 ? num : num * 2);
            ts.add(num);
        }
        int res = ts.last() - ts.first();
        while (ts.last() % 2 == 0) {
            int curr = ts.last();
            ts.remove(curr);
            ts.add(curr / 2);
            res = Math.min(res, ts.last() - ts.first());
        }
        return res;
    }
}
