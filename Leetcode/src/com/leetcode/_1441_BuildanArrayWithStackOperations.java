package com.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 05/11/2020
 * @Description: Simulate
 **/
public class _1441_BuildanArrayWithStackOperations {
    // time:O(n) space:O(n)
    public List<String> buildArray(int[] target, int n) {
        if (target == null || target.length == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        int index = 0;
        for (int i = 1; i <= n && index < target.length; i++) {
            res.add("Push");
            if (target[index] == i) {
                index++;
            } else {
                res.add("Pop");
            }
        }
        return res;
    }
}
