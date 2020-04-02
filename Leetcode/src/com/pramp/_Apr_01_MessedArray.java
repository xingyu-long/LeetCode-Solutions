/*
 * @Date: 2020-04-01 20:55:48
 * 
 * @LastEditors: Clark long
 * 
 * @LastEditTime: 2020-04-01 21:05:06
 */
package com.pramp;

public class _Apr_01_MessedArray {
    // 利用priorityQueue 
    // time:O(nlogK) space:O(K)
    static int[] sortKMessedArray(int[] arr, int k) {
        if (arr == null || arr.length == 0)
            return new int[] {};
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> a - b);
        int sortedIndex = 0;
        for (int i = 0; i < k; i++) {
            pq.offer(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            pq.offer(arr[i]);
            if (pq.size() > k) {
                arr[sortedIndex++] = pq.poll();
            }
        }
        // poll the rest of element
        while (!pq.isEmpty()) {
            arr[sortedIndex++] = pq.poll();
        }
        return arr;
    }
}
