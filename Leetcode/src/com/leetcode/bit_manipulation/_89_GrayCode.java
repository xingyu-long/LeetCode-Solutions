package com.leetcode.bit_manipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 07/18/2020
 * @Description: bit,
 **/
public class _89_GrayCode {

    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            res.add(i ^ (i >> 1));
        }
        return res;
    }

    // time:O(2^n) space:O(n)
    public List<Integer> grayCode2(int n) {
        if (n == 0) {
            return new ArrayList<>(Arrays.asList(0));
        }
        List<Integer> MinusOneList = grayCode2(n - 1);
        int firstBit = 1 << (n - 1);
        for (int i = MinusOneList.size() - 1; i >= 0; i--) { // 反着计算
            MinusOneList.add(firstBit | MinusOneList.get(i));
        }
        return MinusOneList;
    }

    public static void main(String[] args) {
        _89_GrayCode grayCode = new _89_GrayCode();
        grayCode.grayCode(2);
    }
}
