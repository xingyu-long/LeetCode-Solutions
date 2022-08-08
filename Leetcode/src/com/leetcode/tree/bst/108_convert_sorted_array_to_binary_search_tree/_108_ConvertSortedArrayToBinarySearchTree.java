/*
 * @Date: 2019-09-21 10:59:20
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-27 15:10:26
 */
package com.leetcode.tree.bst;


import com.leetcode.common.ConverterForTreeAndString;
import com.leetcode.common.TreeNode;

public class _108_ConvertSortedArrayToBinarySearchTree {
    // time:O(N) space:O(N)
    public static TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return helper(nums, 0, nums.length - 1);
    }

    // 这个的构造不一定和答案一模一样，只是符合 inorder之后升序排列
    public static TreeNode helper(int[] nums, int start, int end) {  // average space : O(log^n)

        if (start > end) return null; //这个条件真的很重要！！！
        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = helper(nums, start, mid - 1); // 这里应该是mid - 1
        node.right = helper(nums, mid + 1, end);
        return node;
    }

    public static void main(String[] args) {
        ConverterForTreeAndString converterForTreeAndString = new ConverterForTreeAndString();
        int[] nums = new int[]{1,2,3,4,5,6,7};
        TreeNode root = sortedArrayToBST(nums);
        System.out.println(converterForTreeAndString.treeNodeToString(root));
    }
}
