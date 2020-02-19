package com.leetcode.binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class _315_CountofSmallerNumbersAfterSelf {

    /**
     * 315. Count of Smaller Numbers After Self
     * When:2019/7/19
     * Difficulty: Hard
     * solution:
     * 首先逆序，然后使用Binary Search Tree的性质，记录其比自己小的节点个数存在node.num中
     */

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
            if (root.right != null) {
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
        if (nums == null || nums.length == 0) return new ArrayList<>();
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
        if (sortedArray.size() == 0) return 0;
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
        if (sortedArray.get(right) < target) return right + 1;
        if (sortedArray.get(left) >= target) return left;
        else return right;
    }
}
