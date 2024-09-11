package com.leetcode.array;

public class _275_HIndexII {
    /**
     *  275. H-Index II
     *  When:2019/7/25
     *  review1: 2019/8/30
     *  Difficulty: Medium
     * @param citations
     * @return
     */
    // https://leetcode.com/problems/h-index-ii/discuss/71063/Standard-binary-search
    // time:O(lgN) space:O(1)
    public int hIndex(int[] citations) {
        // 条件是要有 有h篇 引用h以上的。
        if (citations == null || citations.length == 0) return 0;
        int left = 0;
        int right = citations.length - 1;
        int n = citations.length;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] >= n - mid) { // 右边有这么多篇nums[mid]以上的。
                right = mid;
            } else {
                left = mid;
            }
        }
        // check left; 数量多些，hindex尽量高
        if (citations[left] >= n - left) return n - left;
        if (citations[right] >= n - right) return n - right;
        return 0;
    }
}