package com.leetcode.binarySearch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _315_CountofSmallerNumbersAfterSelf {

    /**
     *  315. Count of Smaller Numbers After Self
     *  When:2019/7/19
     *  Difficulty: Hard
     *  solution:
     *  首先逆序，然后使用Binary Search Tree的性质，记录其比自己小的节点个数存在node.num中
     *
     */

    private class BSTNode {
        public int val;
        public BSTNode left;
        public BSTNode right;
        public BSTNode(int x) {
            val = x;
            num = 0;
        }
        public int num;
    }

    // time:O(nlogn) space:O(1)
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
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
            if (root.right != null) {
                return bstInsert(root.right, newNode, numOfSmall);
            } else {
                root.right = newNode;
                return numOfSmall;
            }
        }
    }
}
