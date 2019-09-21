package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _236_LowestCommonAncestorOfABinaryTree {

    TreeNode res = null;

    /**
     * 236. Lowest Common Ancestor of a Binary Tree
     * When: 2019/04/22
     *
     * solution:
     * 设置left, mid, right三个变量
     * 只要当前有一个等于p或者q 则就会有true 当三个 加起来 > 2这个时候就已经是该节点了
     *
     * Test case:
     *  p = 4, q = 10
     *           5
     *          / \
     *         4   9
     *        /   / \
     *       2   6  10
     *
     *  5 ---> 4 (found)---> 2
     *  then left = 1;
     *  5 ---> 9 ---> 6
     *  backtrack
     *  6 ---> 9
     *  5 ---> 9 ---> 10 (found)
     *  right = 1
     *  只有5这个点的时候left和right = 1 满足
     *  return 5
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        helper(root, p, q);
        return res;
    }

    //time: O(n) space:O(n)
    public boolean helper(TreeNode current, TreeNode p, TreeNode q) {
        if (current == null) return false;

        int left = helper(current.left, p, q) ? 1 : 0;

        int right = helper(current.right, p, q) ? 1 : 0;

        int mid = (current == p || current  == q) ? 1 : 0;

        if (left + mid + right >= 2) res = current;

        return (left + mid + right > 0);
    }

    // 如果左边或者右边没有的情况，则只会找到第一个节点后返回（这个节点肯定就是共同父节点）
    public static TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (root.val == p.val || root.val == q.val) return root;
        TreeNode left = lowestCommonAncestor2(root.left, p, q);
        TreeNode right = lowestCommonAncestor2(root.right, p, q);

        if (left == null) return right;
        if (right == null) return left;
        // 表示 left !=null && right != null 的情况
        return root;
    }

    // 类比于BST的方法
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        if (isExist(root.right, p) && isExist(root.right, q)) {
            return lowestCommonAncestor(root.right, p, q);
        } else if (isExist(root.left, p) && isExist(root.left, q)) {
            return lowestCommonAncestor(root.left, p, q);
        } else {
            return root;
        }
    }

    public boolean isExist(TreeNode root, TreeNode target) {
        if (root == null) return false;
        if (root == target) return true;
        return isExist(root.left, target) || isExist(root.right, target);
    }


    public static void main(String[] args){
        TreeNode root = new TreeNode(1);
        root.left  = new TreeNode(2);
        root.right = new TreeNode(3);
        TreeNode left = root.left;
        TreeNode right = root.right;
        left.left = new TreeNode(4);
        left.right = new TreeNode(5);
        right.left = new TreeNode(6);
        right.right = null;
        TreeNode p = new TreeNode(2);
        TreeNode q = new TreeNode(5);

        System.out.println(lowestCommonAncestor2(root, p, q).val);

    }
}


