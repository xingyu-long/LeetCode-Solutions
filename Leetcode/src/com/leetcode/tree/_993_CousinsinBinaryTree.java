package com.leetcode.tree;

import com.leetcode.common.TreeNode;
import com.leetcode.common.ConverterForTreeAndString;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Date: 05/07/2020
 * @Description: Tree
 **/
public class _993_CousinsinBinaryTree {

    // BFS.
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null) {
            return true;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean isExistX = false;
            boolean isExistY = false;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.val == x) {
                    isExistX = true;
                }
                if (node.val == y) {
                    isExistY = true;
                }
                // 每次check是否为sibling。
                if (node.left != null && node.right != null) {
                    if (node.left.val == x && node.right.val == y) {
                        return false;
                    }
                    if (node.left.val == y && node.right.val == x) {
                        return false;
                    }
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            if (isExistX && isExistY) {
                return true;
            }
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
        return dx.depth == dy.depth && dx.parent != dy.parent;
    }

    public Data dfs(TreeNode root, TreeNode prev, int depth, int target) {
        if (root == null) {
            return new Data(null, 0);
        }
        depth += 1;
        if (root.val == target) {
            return new Data(prev, depth);
        }
        Data left = dfs(root.left, root, depth, target);
        Data right = dfs(root.right, root, depth, target);
        if (left.depth != 0) {
            return left;
        } else {
            return right;
        }
    }
    
    public boolean isCousins3(TreeNode root, int x, int y) {
        
        int[] xresult = find(root, root, x, 0);
        int[] yresult = find(root, root, y, 0);
        return xresult[0] == yresult[0] && xresult[1] != yresult[1];
    }
    
    private int[] find(TreeNode root, TreeNode prev, int target, int depth) {
        if (root == null) {
            return null;
        }
        if (root.val == target) {
            return new int[]{depth, prev.val};
        }
        int[] left = find(root.left, root, target, depth + 1);
        if (left != null) return left;
        int[] right = find(root.right, root, target, depth + 1);
        if (right != null) return right;
        return null;
    }
}
