package com.leetcode.prefixSum;

import java.util.ArrayList;
import java.util.List;

public class _1352_ProductoftheLastKNumbers {
    // time:O(1)
    // space:O(n)
    // 主要的考点就是prefix product以及处理0的情况。题目也说了没有小于0的情况
    // 还是从前往后看，然后对于0来说，就把当前的数组清掉，然后getProduct这里就注意大小
    List<Integer> list;
    // range sum.
    public _1352_ProductoftheLastKNumbers() {
        add(0);
    }

    public void add(int num) {
        if (num > 0)
            list.add(list.get(list.size() - 1) * num);
        else {
            list = new ArrayList<>();
            list.add(1);// 这个没有影响
        }
    }

    public int getProduct(int k) {
        int n = list.size();
        return k < n ? (list.get(n - 1) / list.get(n - k - 1)) : 0;
    }
}
