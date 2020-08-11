/*
 * @Date: 2019-12-27 17:54:39
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-22 13:59:51
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import com.leetcode.common.ConverterForTreeAndString;

import java.util.LinkedList;
import java.util.Queue;

public class _993_CousinsinBinaryTree {
    // BFS.
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isExistX = false;
            boolean isExistY = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val == x) isExistX = true;
                if (node.val == y) isExistY = true;
                // 每次check是否为sibling。
                if (node.left != null && node.right != null) {
                    if (node.left.val == x && node.right.val == y) return false;
                    if (node.left.val == y && node.right.val == x) return false;
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            if (isExistX && isExistY) return true;
        }
        return false;
    }


    class Data {
        TreeNode parent;
        int depth;
        
        public Data(TreeNode p, int d) {
            parent = p;
            depth = d;
        }
    }
    public boolean isCousins2(TreeNode root, int x, int y) {
       // return parent 以及depth吧
        Data dx = dfs(root, null, 0, x);
        Data dy = dfs(root, null, 0, y);
        if (dx.depth != dy.depth) return false;
        else {
            if (dx.parent != dy.parent) return true;
            return false;
        }
    }
    
    public Data dfs(TreeNode root, TreeNode prev, int depth, int target) {
        if (root == null) return new Data(null, 0);
        depth += 1;
        if (root.val == target) {
            return new Data(prev, depth);
        }
        Data left = dfs(root.left, root, depth, target);
        Data right = dfs(root.right, root, depth, target);
        if (left.depth != 0) return left;
        else return right;
    }
    
    public static void main(String[] args) {
        _993_CousinsinBinaryTree cousinsinBinaryTree = new _993_CousinsinBinaryTree();
        String input = "[1,2,3,4]";
        int x = 4, y = 3;
        ConverterForTreeAndString converter = new ConverterForTreeAndString();
        TreeNode root = converter.stringToTreeNode(input);
        System.out.println(root.val);
        cousinsinBinaryTree.isCousins4(root, x, y);
    }
}
