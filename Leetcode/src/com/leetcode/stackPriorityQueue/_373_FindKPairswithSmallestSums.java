package com.leetcode.stackPriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _373_FindKPairswithSmallestSums {

    /**
     * 373. Find K Pairs with Smallest Sums
     * When:2019/9/8
     * Difficulty: Medium
     *
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    // time: m * n space:O(m * n) 浪费了空间并且降低了时间复杂度
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(
                (a, b) -> (a.get(0) + a.get(1) - b.get(0) - b.get(1)));

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                List<Integer> temp = new ArrayList<>();
                temp.add(nums1[i]);
                temp.add(nums2[j]);
                pq.add(new ArrayList<>(temp));
            }
        }

        while (k > 0 && !pq.isEmpty()) {
            res.add(pq.poll());
            k--;
        }
        return res;
    }

    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
    // time:O(KLogK) space:O(k)
    public List<List<Integer>> kSmallestPairs2(int[] nums1, int[] nums2, int k) {

        List<List<Integer>> res = new ArrayList<>();

        if (nums1.length == 0 || nums2.length == 0 || k == 0) {
            return res;
        }

        PriorityQueue<List<Integer>> pq = new PriorityQueue<>(
                (a, b) -> (a.get(0) + a.get(1) - b.get(0) - b.get(1)));

        // 先把 nums[i]与nums2[0]全部配对
        // 最后一个数是来记录nums2的位置
        for (int i = 0; i < nums1.length && i < k; i++) {
            pq.offer(new ArrayList<>(Arrays.asList(nums1[i], nums2[0], 0)));
        }

        while (!pq.isEmpty() && k -- > 0) {
            List<Integer> cur = pq.poll();
            res.add(new ArrayList<>(Arrays.asList(cur.get(0), cur.get(1))));
            if (cur.get(2) == nums2.length - 1) continue;
            // 加入下一个nums2
            pq.offer(new ArrayList<>(Arrays.asList(cur.get(0), nums2[cur.get(2) + 1], cur.get(2) + 1)));
        }
        return res;
    }
}
