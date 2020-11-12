package com.leetcode.array;

import java.util.Arrays;

public class _274_HIndex {
    // time:O(nlogN) space:O(1)
    // 反着想
    public int hIndex(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int n = citations.length;
        Arrays.sort(citations);
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (res >= citations[i]) {
                break;
            } else {
                res++;
            }
        }
        return res;
    }

    // 正着想
    public int hIndex2(int[] citations) {
        if (citations == null || citations.length == 0) {
            return 0;
        }
        int n = citations.length;
        Arrays.sort(citations);
        for (int i = 0; i < n; i++) {
            if (citations[i] >= n - i) return n - i;
        }
        return 0;
    }
    // https://leetcode.com/problems/h-index/discuss/70810/A-Clean-O(N)-Solution-in-Java
    // https://leetcode.com/problems/h-index/discuss/70768/Java-bucket-sort-O(n)-solution-with-detail-explanation

    //time:O(n) space:O(1)
    public int hIndex3(int[] citations) {
        int len = citations.length;
        int[] count = new int[len + 1];

        for (int c : citations) {
            if (c >= len) {
                count[len]++;
            } else {
                count[c]++;
            }
        }

        int total = 0;
        for (int i = len; i >= 0; i--) {
            total += count[i];
            if (total >= i) { //表示一旦找到，就返回其下标，表示大于i的有total这么多个
                return i;
            }
        }
        return 0;
    }
}