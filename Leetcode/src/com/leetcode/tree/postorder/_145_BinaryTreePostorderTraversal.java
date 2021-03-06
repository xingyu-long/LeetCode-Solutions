package com.leetcode.tree.postorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class _145_BinaryTreePostorderTraversal {
    /**
     * 145. Binary Tree Postorder Traversal
     * When: 2019/04/12
     *
     * solution:
     * 最普通的后序排序，并且这里的输入是root节点而不是数组所以自己不用构造才对。
     * 后序输出，则就是左遍历到底 再右遍历 再输出值
     * @param root
     * @return
     */
    //time:O(n) space:O(n) 最差的情况就是O(n)这颗树是偏向于一边。
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root);
        return res;
    }

    public static void helper(List<Integer> res, TreeNode root) {
        if (root == null) return;
        helper(res, root.left);
        helper(res, root.right);
        res.add(root.val);
    }

    // 非递归的形式 利用前序的思想，只需要稍作修改，每次将结果放到前面
    // list与linkedlist是兼容的
    public static List<Integer> postorderTraversal2(TreeNode root) {
        if (root == null) return new LinkedList<>();
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            res.addFirst(cur.val);
            if (cur.left != null) stack.push(cur.left);
            if (cur.right != null) stack.push(cur.right);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = null;
        root.right = new TreeNode(2);
        TreeNode right = root.right;
        right.left = new TreeNode(3);
        System.out.println(postorderTraversal(root).toString());
    }
}
