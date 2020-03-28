/*
 * @Date: 2020-01-03 10:13:32
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-27 17:07:39
 */
package com.leetcode.tree.bst;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _1305_AllElementsinTwoBinarySearchTrees {
    // 核心思想依然是使用非递归的中序遍历算法。这个需要多注意
    public void pushLeft(Stack<TreeNode> stack, TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    // time:O(M + N)
    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();
        pushLeft(s1, root1);
        pushLeft(s2, root2);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            Stack<TreeNode> s = null;
            if (!s1.isEmpty() && !s2.isEmpty()) {
                s = s1.peek().val > s2.peek().val ? s2 : s1;
            } else if (s1.isEmpty()) {
                s = s2;
            } else if (s2.isEmpty()) {
                s = s1;
            }
            TreeNode current = s.pop();
            res.add(current.val);
            current = current.right;
            pushLeft(s, current); // 这个相当于那个前面的while过程。
        }
        return res;
    }
}
