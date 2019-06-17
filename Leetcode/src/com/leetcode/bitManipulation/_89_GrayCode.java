package com.leetcode.bitManipulation;

import java.util.ArrayList;
import java.util.List;

public class _89_GrayCode {

    /**
     *  89. Gray Code
        When: 2019/06/17

        solution:
        G(i) = i ^ (i / 2)
     * @param n
     * @return
     */
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            res.add(i ^ i >> 1);
        }
        return res;
    }
}
