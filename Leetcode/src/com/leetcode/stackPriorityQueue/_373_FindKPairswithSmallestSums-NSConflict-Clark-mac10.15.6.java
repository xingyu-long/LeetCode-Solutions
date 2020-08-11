package com.leetcode.stackPriorityQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class _373_FindKPairswithSmallestSums {
    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
    // time:O(KLogK) space:O(k)
    // 需要把左边的所有* 右边第一个元素放入pq中。这样能确保，左边出现更小的时候可以poll出来
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) return res;
        int n1 = nums1.length;
        int n2 = nums2.length;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) ->
                (nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]]));
        for (int i = 0; i < n1 && pq.size() < k; i++) {
            pq.offer(new int[]{i, 0});
        }

        while (res.size() < k && !pq.isEmpty()) {
            int[] curr = pq.poll();
            int idx1 = curr[0];
            int idx2 = curr[1];
            if (idx2 + 1 < n2) pq.offer(new int[]{idx1, idx2 + 1});
            res.add(Arrays.asList(nums1[idx1], nums2[idx2]));
        }

        return res;
    }
}
