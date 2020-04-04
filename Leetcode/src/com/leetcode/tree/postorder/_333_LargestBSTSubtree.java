/*
 * @Date: 2019-12-28 16:51:10
 * 
 * @LastEditors: Clark long
 * 
 * @LastEditTime: 2020-04-02 13:46:15
 */
package com.leetcode.tree.postorder;

import com.leetcode.common.ConverterForTreeAndString;
import com.leetcode.common.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class _333_LargestBSTSubtree {

    // time: O(n^2) space:O(n) 因为每次都要遍历验证是否为BST并且计算其count;
    public int largestBSTSubtree(TreeNode root) {
        if (root == null)
            return 0;
        if (isValidBST(root, null, null))
            return countNodes(root);
        else {
            int left = largestBSTSubtree(root.left);
            int right = largestBSTSubtree(root.right);
            return Math.max(left, right);
        }
    }

    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        int left = countNodes(root.left);
        int right = countNodes(root.right);
        return left + right + 1;
    }

    public boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null)
            return true;
        if (min != null && root.val <= min)
            return false;
        if (max != null && root.val >= max)
            return false;
        boolean left = isValidBST(root, min, root.val);
        boolean right = isValidBST(root, root.val, max);
        return left && right;
    }

    // 接下来是O(n)的解法，利用post order。
    public class Node {
        boolean isBST;
        int count;
        int min;
        int max;

        public Node() {
            this.isBST = true;
            this.count = 0;
            this.min = Integer.MAX_VALUE;
            this.max = Integer.MIN_VALUE;
        }
    }

    public int largestBSTSubtree(TreeNode root) {
        // 应该需要构造一个结构？ count, min, max, node
        if (root == null)
            return 0;
        Node res = dfs(root);
        return res.count;
    }

    public Node dfs(TreeNode root) {
        if (root == null) {
            return new Node();
        }

        Node left = dfs(root.left);
        Node right = dfs(root.right);

        Node node = new Node();
        // 这里的条件尤其重要，就是当前值应该是应该左子树能够达到的最大值，是右子树的最小值
        if (!left.isBST || !right.isBST || root.val >= right.min || root.val <= left.max) {
            node.isBST = false;
            node.count = Math.max(left.count, right.count);
            return node;
        }
        node.isBST = true;
        // 这里的min，max则应该是从小到大。
        node.min = Math.min(left.min, root.val);
        node.max = Math.max(right.max, root.val);
        node.count = left.count + right.count + 1;
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
