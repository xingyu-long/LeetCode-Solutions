package com.leetcode.binary_search;

public class _278_FirstBadVersion {

    /**
     * 278. First bad version
     * When: 2019/7/19
     * review1:2019/10/14
     * Difficulty: Easy
     * solution: 就利用普通的binary search，如果发现有的话，那么肯定就在左边，没有的话就在右边。
     * 最后left总是比right大1
     * @param n
     * @return
     */
    // more elegant way Template time:O(logN) space:O(1)
    public int firstBadVersion2(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (isBadVersion(mid)) {
                right = mid;
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
