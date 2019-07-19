package com.leetcode.binarySearch;

public class _278_FirstBadVersion {

    /**
     *  278. First bad version
     *  When: 2019/7/19
     *  Difficulty: Easy
     *  solution: 就利用普通的binary search，如果发现有的话，那么肯定就在左边，没有的话就在右边。
     *  最后left总是比right大1
     *
     * @param n
     * @return
     */
    // time:O(logn) space:O(1)
    public int firstBadVersion(int n) {
        int left = 1;
        int right = n;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (isBadVersion(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public boolean isBadVersion(int mid) {
        return true;
    }
}
