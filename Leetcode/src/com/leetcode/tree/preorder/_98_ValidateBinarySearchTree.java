package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

public class _98_ValidateBinarySearchTree {

    /**
     *  98. Validate Binary Search Tree
     *  When: 2019/04/23
     *  Review1:2019/7/27
     *  review2:2019/9/11
     * solution: 利用递归的形式，并且主要是 使用 min 和 max
     * 在左子树的话，将当前的root.val 设置为max （如果左边有任意一个大于max 则false
     * 在右子树的话，将当前的root.val 设置为min （如果右边有任意一个小于min 则false
     *
     * test case:
     *        5
     *       /  \
     *      3    6
     *     / \  / \
     *    2  4 7  10
     * (1) min = null; max = null
     * (2) 左子树 ->进入3这个节点 min = null; max = 5
     *     (2.1) ->进入2这个节点 min = null, max = 3  true
     *     (2.2) ->进入4这个节点 min = 3; max = 3 true
     * (3) 右子树 ->进入6这个节点 min = 5; max = null
     *     (3.1) ->进入7这个节点 min = 5, max = 6  -> max <= root.val return false
     *     (3.2) ->进入10这个节点 min = 6, max = null return true
     * (2) true && (3) false
     * return false
     *
     * Time: O(n)
     * Space: O(logN)
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        return helper(root, null, null);
    }

    // 左边设置最大值，右边设置最小值
    public static boolean helper(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        //针对于右子树 （理应当 所有的值大于min）
        if (min != null && min >= root.val) return false;
        //针对于左子树 （理应当 所有的值小于max）
        if (max != null && max <= root.val) return false;
        // 左边的话 即是当前节点是最大的情况；右边的话，当前节点是最小值
        return helper(root.left, min, root.val) && helper(root.right, root.val, max);
    }
}
