package com.leetcode.tree.postorder;

import com.leetcode.common.ConverterForTreeAndString;
import com.leetcode.common.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _333_LargestBSTSubtree {

    // time: O(n^2) space:O(n) 因为每次都要遍历验证是否为BST并且计算其count;
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        if (isValidBST(root, null, null)) return countNodes(root);
        else {
            int left = largestBSTSubtree(root.left);
            int right = largestBSTSubtree(root.left);
            return Math.max(left, right);
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }

    public boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && root.val <= min) return false;
        if (max != null && root.val >= max) return false;
        boolean left = isValidBST(root, min, root.val);
        boolean right = isValidBST(root, root.val, max);
        return left && right;
    }

    // 接下来是O(n)的解法，利用post order。
    public static class SearchNode {
        boolean isBST;
        int min; // 其边界
        int max;
        int size; // max size of BST subtree

        public SearchNode() {
            isBST = true;
            min = Integer.MAX_VALUE;
            max = Integer.MIN_VALUE;
            size = 0;
        }
    }

    // 构建searchNode 然后用一个post order就完成。
    public static int largestBSTSubtree2(TreeNode root) {
        SearchNode searchNode = helperByPostOrder(root);
        return searchNode.size;
    }

    public static SearchNode helperByPostOrder(TreeNode root) {
        if (root == null) return new SearchNode();

        SearchNode left = helperByPostOrder(root.left);
        SearchNode right = helperByPostOrder(root.right);

        SearchNode node = new SearchNode();
        // 判断是否符合bst的性质。
        if (!left.isBST || !right.isBST || root.val >= right.min || root.val <= left.max) {
            node.isBST = false;
            // 利用这里可以一直传递其最大的size是多少。
            node.size = Math.max(left.size, right.size);
            return node;
        }
        node.isBST = true;
        node.size = left.size + right.size + 1;
        node.min = Math.min(left.min, root.val);
        node.max = Math.max(right.max, root.val);
        return node;
    }

    @Test
    void unitTest() {
        ConverterForTreeAndString converter = new ConverterForTreeAndString();
        String str = "[10,5,15,1,8,null,7]";
        TreeNode root = converter.stringToTreeNode(str);
        assertEquals(3, largestBSTSubtree2(root));
    }
}