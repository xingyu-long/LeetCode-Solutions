package com.leetcode.bfsANDdfs;

import com.leetcode.common.NestedInteger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _364_NestedListWeightSumII {

    /**
     * 364. Nested List Weight Sum II
     * time:2019/7/25
     * review1:10/31/2019
     * @param nestedList
     * @return
     */

    //time:O(n) space:O(n)
    //解释: https://www.cnblogs.com/grandyang/p/5615583.html
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        int sum = 0;
        int res = 0;
        while (!nestedList.isEmpty()) {
            List<NestedInteger> nextList = new LinkedList<>();
            for (NestedInteger nest : nestedList) {
                if (nest.isInteger()) {
                    sum += nest.getInteger();
                } else {
                    nextList.addAll(nest.getList());
                }
            }
            res += sum;
            nestedList = nextList;
        }
        return res;
    }

    // 上面的和这个本质是一样的！
    // eg: [1,1], 2, [1, 1]
    // nextList = [1, 1, 1, 1] res = 2
    // res = 2 + helper(nexList, res)
    // helper那里就是 res + 1+1+1+1 = 2 + 4 = 6;
    // 一共就是8
    private int helper(List<NestedInteger> nestedList, int res) {
        List<NestedInteger> nextList = new LinkedList<>();
        for (NestedInteger nest : nestedList) {
            if (nest.isInteger()) {
                res += nest.getInteger();
            } else {
                nextList.addAll(nest.getList());
            }
        }
        res += nextList.isEmpty() ? 0 : helper(nestedList, res);
        return res;
    }

}
