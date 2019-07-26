package com.leetcode.tree;


import com.leetcode.common.TreeNode;

public class _111_MinimumDepthOfBinaryTree {

    /**
     *  111. Minimum Depth of Binary Tree
     *  When: 2019/04/18
     *  Review1: 2019/7/26
     *
     * solution：
     * 1. 依然使用递归的先序遍历 然后这里不是加val而是x += 1即可
     * @param root
     * @return
     */
    // time:O(n), space: O(n)
    private int res = Integer.MAX_VALUE;

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        helper(root, 1);
        return res;
    }

    public void helper(TreeNode root, int depth) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res = Math.min(res, depth);
        }
        helper(root.left, depth + 1);
        helper(root.right, depth + 1);
    }

    /**
     * solution2：
     * 利用math.max 来计算其存在左右子节点或许为空的情况（所以只计算有的情况即用max）
     *
     * test case:
     *     1
     *    / \
     *   2  3
     *   \
     *   4
     *
     *
     *    (1) minDepth(root.left)
     *          -> 进入2这个节点 # return 1+1 = 2;
     *            -> (1.1) 进入if语句
     *                (1.1.1)minDepth(2.left) -> null -> 0
     *                (1.1.2)minDepth(2.right) # return 1
     *                         进入4这个节点 一样进入if语句但是两个都是空 所以这里return 1
     *                (1.1.3)max(xxx) + 1
     *    (2) minDepth(root.right)
     *         -> 进入3这个节点 一样进入if语句但是两个都是空 所以这里返回 1
     *    (3) return min (...)  + 1
     *
     *    综合就是 return Math.min(2, 1) + 1 -> 2;则右边的时候最低 路径为 "1->3"
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        // 这一步需要好好的思考其过程 （最后也有+1 所以之前的值 就会相应的少1 ，
        // 并且需要max是因为只要求存在子节点的那一部分而不是为 "空的部分" ）
        if (root.left == null || root.right == null) {
            return Math.max(minDepth2(root.left), minDepth2(root.right)) + 1;
        }
        return Math.min(minDepth2(root.left), minDepth2(root.right)) + 1;
    }
}
