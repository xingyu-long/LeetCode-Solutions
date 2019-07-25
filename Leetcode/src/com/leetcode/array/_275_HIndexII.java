package com.leetcode.array;

public class _275_HIndexII {
    /**
     *  275. H-Index II
     *  When:2019/7/25
     *  Difficulty: Medium
     * @param citations
     * @return
     */
    // https://leetcode.com/problems/h-index-ii/discuss/71063/Standard-binary-search
    // time:O(lgN) space:O(1)
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) return 0;
        int n = citations.length;
        int left = 0, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= n - mid) { // 写成 citations[n - mid - 1] <= mid
                right = mid; // 找到第一个对于右边的所有数，左边都小于
            } else {
                left = mid + 1;
            }
        }
        return n - left;
    }
}