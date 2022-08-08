/*
 * @Date: 2019-11-17 10:27:02
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-27 21:39:26
 */
package com.leetcode.tree.bst;

import com.leetcode.common.ConverterForTreeAndString;
import com.leetcode.common.TreeNode;

import java.util.Stack;

public class _230_KthSmallestElementInABST {
    
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
        ConverterForTreeAndString converterForTreeAndString = new ConverterForTreeAndString();
        TreeNode root = converterForTreeAndString.stringToTreeNode(s);
        System.out.println(kthSmallest(root,1));
    }

}
