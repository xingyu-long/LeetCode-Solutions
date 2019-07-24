package com.leetcode.array;

import java.util.Arrays;

public class _274_HIndex {

    /**
     *  274. H-Index
     *  When:2019/7/23 还有另外一种方法
     *  Difficulty: Medium
     *  solution:
     *  先进行排序操作 从最后一个元素向前循环，大于count就count++，然后走到小于count的地方为止
     *  这里的count就是表示大于count的个数。剩下的就小于
     * @param citations
     * @return
     */

    // time:O(nlogN) space:O(1)
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int res = 0;
        while (res < citations.length
                && citations[citations.length - 1 - res] > res) {
            res++;
        }
        return res;
    }


    // https://leetcode.com/problems/h-index/discuss/70810/A-Clean-O(N)-Solution-in-Java
    // https://leetcode.com/problems/h-index/discuss/70768/Java-bucket-sort-O(n)-solution-with-detail-explanation

    //time:O(n) space:O(n)
    public int hIndex2(int[] citations) {
        int len = citations.length;
        int[] count = new int[len + 1];

        for (int c : citations) {
            if (c > len) {
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