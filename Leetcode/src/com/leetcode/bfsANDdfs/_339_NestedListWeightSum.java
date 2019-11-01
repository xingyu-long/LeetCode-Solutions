package com.leetcode.bfsANDdfs;

import com.leetcode.common.NestedInteger;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _339_NestedListWeightSum {
    /**
     * 339. Nested List Weight Sum
     * When:2019/7/25
     * review1:10/31/2019
     * Difficulty: Medium
     * <p>
     * Given a nested list of integers, return the sum of all integers in the list weighted by their depth.
     * <p>
     * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
     * <p>
     * Example 1:
     * Given the list [[1,1],2,[1,1]], return 10. (four 1's at depth 2, one 2 at depth 1)
     * <p>
     * Example 2:
     * Given the list [1,[4,[6]]], return 27.
     * (one 1 at depth 1, one 4 at depth 2, and one 6 at depth 3; 1 + 4*2 + 6*3 = 27)
     * <p>
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
