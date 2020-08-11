package com.leetcode.rangeSum;

import com.leetcode.tree.FenwickTree;

/**
 * @Date: 04/11/2020
 * @Description: RangeSum, Segement Tree, Fenwick Tree.
 **/
public class _307_RangeSumQueryMutable {

    int[] nums;
    FenwickTree tree;

    public _307_RangeSumQueryMutable(int[] nums) {
        this.nums = nums;
        tree = new FenwickTree(nums.length);
        for (int i = 0; i < nums.length; i++) {
            tree.update(i + 1, nums[i]);
        }
    }

    public void update(int i, int val) {
        tree.update(i + 1, val - nums[i]);
        nums[i] = val;
    }

    public int sumRange(int i, int j) {
        return tree.query(j + 1) - tree.query(i);
    }
}
