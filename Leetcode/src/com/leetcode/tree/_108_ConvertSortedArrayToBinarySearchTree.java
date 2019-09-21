package com.leetcode.tree;


import com.leetcode.common.MainClass;
import com.leetcode.common.TreeNode;

public class _108_ConvertSortedArrayToBinarySearchTree {
    /**
     * 108. Convert Sorted Array to Binary Search Tree
     * When: 2019/04/22
     * review1: 2019/9/9
     * review2: 2019/9/21
     * solution： 利用递归的方式 让其返回，但是要注意那个临界条件！！！
     *
     * time: O(n)
     * Space: O(n)
     * @param nums
     * @return
     */
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
        MainClass mainClass = new MainClass();
        int[] nums = new int[]{1,2,3,4,5,6,7};
        TreeNode root = sortedArrayToBST(nums);
        System.out.println(mainClass.treeNodeToString(root));
    }
}
