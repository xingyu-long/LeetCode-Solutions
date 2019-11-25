package com.leetcode.tree.bst;

import com.leetcode.common.MainClass;
import com.leetcode.common.TreeNode;

import java.util.Stack;

public class _230_KthSmallestElementInABST {


    /**
     * 230. Kth Smallest Element in a BST
     * When: 2019/04/25
     * review1:2019/9/21
     * solution: BST中序遍历的时候则就是有序的（注意那个变量的变化）
     * time: O(n)
     * space: O(n) 因为递归
     * @param root
     * @param k
     * @return
     */
    private static int count = 0;
    private static int res = 0;
    // DFS
    // time: O(k) space:O(k) -> worse O(n)
    public static int kthSmallest(TreeNode root, int k) {
        if (root == null) return 0;
        count = k;
        helper(root);
        return res;
    }

    public static void helper(TreeNode root) {
        if (root == null) return;
        helper(root.left);
        if (--count == 0) {
            res = root.val;
        }
        helper(root.right);
    }



    Stack<TreeNode> stack = new Stack<>();
    // BFS
    // worse time:O(K + h) space: O(k + h)
    public int kthSmallest2(TreeNode root, int k) {
        if (root == null) return 0;
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (--k == 0) return cur.val;
            cur = cur.right;
        }
        return -1;
    }


    public static void main(String[] args) {
        String s = "[3,1,4,null,2]";
        MainClass mainClass = new MainClass();
        TreeNode root = mainClass.stringToTreeNode(s);
        System.out.println(kthSmallest(root,1));
    }

}
