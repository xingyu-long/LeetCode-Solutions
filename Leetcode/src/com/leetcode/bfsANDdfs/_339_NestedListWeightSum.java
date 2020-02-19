package com.leetcode.bfsANDdfs;

import com.leetcode.common.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _339_NestedListWeightSum {
    /**
     * 339. Nested List Weight Sum
     * 1/1/2020
     * solution:
     * 记录层数 然后和当前的是integer的情况相加
     *
     * @param nestedList
     * @return
     */
    // time:O(n) space:O(n)
    //如何弄出数据并且决定层数
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        return helper(nestedList, 1);
    }

    //time:O(n) space:O(n)
    public int helper(List<NestedInteger> nestedList, int depth) {
        int res = 0;
        for (NestedInteger nest : nestedList) {
            if (nest.isInteger()) {
                res += nest.getInteger() * depth;
            } else {
                res += helper(nest.getList(), depth + 1);
            }
        }
        return res;
    }

    // BFS
    // time:O(n) space:O(n)
    public int depthSum2(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        int depth = 1;
        int res = 0;
        Queue<NestedInteger> queue = new LinkedList<>(nestedList);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                NestedInteger nest = queue.poll();
                if (nest.isInteger()) {
                    res += nest.getInteger() * depth;
                } else {
                    queue.addAll(nest.getList());
                }
            }
            depth++; // 下一波的integer
        }
        return res;
    }
}
