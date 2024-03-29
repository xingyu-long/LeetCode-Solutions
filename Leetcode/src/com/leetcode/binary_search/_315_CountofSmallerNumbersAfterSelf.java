package com.leetcode.binary_search;

import com.leetcode.tree.FenwickTree;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Date: 7/19/2019, 04/11/2020
 * @Description: RangeSum, Segement Tree, Fenwick Tree.
 **/
public class _315_CountofSmallerNumbersAfterSelf {

    private class BSTNode {

        public int val;
        public BSTNode left;
        public BSTNode right;
        public int num;

        public BSTNode(int x) {
            val = x;
            num = 0;
        }
    }

    // time:O(nlogn) space:O(1)
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        BSTNode root = new BSTNode(nums[nums.length - 1]);
        for (int i = nums.length - 2; i >= 0; i--) {
            int numOfSmall = 0;
            BSTNode newNode = new BSTNode(nums[i]);
            numOfSmall = bstInsert(root, newNode, numOfSmall);
            res.add(numOfSmall);
        }
        Collections.reverse(res);
        res.add(0); // 最后一个元素，后面肯定没有比它小的数
        return res;
    }

    // KEY!
    public int bstInsert(BSTNode root, BSTNode newNode, int numOfSmall) {
        if (newNode.val <= root.val) {
            root.num++;
            if (root.left != null) {
                return bstInsert(root.left, newNode, numOfSmall);
            } else {
                root.left = newNode;
                return numOfSmall;
            }
        } else {
            numOfSmall += root.num + 1;
            if (root.right != null) { // 这种情况下numOfSmall不受影响的
                return bstInsert(root.right, newNode, numOfSmall);
            } else {
                root.right = newNode;
                return numOfSmall;
            }
        }
    }


    // 从后到前构造sortArray 并且利用binary search查找index。这个index就是跟大于的个数相同
    // time:O(N^2) space:O(N) 因为add(index, num)需要耗费O(n)
    public List<Integer> countSmaller2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        Integer[] res = new Integer[nums.length];
        List<Integer> sortedArray = new ArrayList<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            int index = findIndex(sortedArray, nums[i]);// 在sort找位置
            res[i] = index;
            sortedArray.add(index, nums[i]);
        }
        return Arrays.asList(res);
    }

    public int findIndex(List<Integer> sortedArray, int target) {
        if (sortedArray.size() == 0) {
            return 0;
        }
        int left = 0;
        int right = sortedArray.size() - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (sortedArray.get(mid) >= target) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // 这个比较关键。
        if (sortedArray.get(right) < target) {
            return right + 1;
        }
        if (sortedArray.get(left) >= target) {
            return left;
        } else {
            return right;
        }
    }

    // 利用binary index tree
    public List<Integer> countSmaller3(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new ArrayList<>();
        }
        int[] sorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sorted);
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> ranks = new HashMap<>();
        int rank = 1;
        for (int i = 0; i < sorted.length; i++) {
            if (i == 0 || sorted[i] != sorted[i - 1]) {
                ranks.put(sorted[i], rank++);
            }
        }
        FenwickTree tree = new FenwickTree(ranks.size());
        for (int i = nums.length - 1; i >= 0; i--) {
            int sum = tree.query(ranks.get(nums[i]) - 1);
            res.add(sum);
            tree.update(ranks.get(nums[i]), 1);
        }
        Collections.reverse(res);
        return res;
    }
}
