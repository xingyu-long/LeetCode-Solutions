package com.leetcode;


public class _111_MinimumDepthOfBinaryTree {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 111. Minimum Depth of Binary Tree
     * When: 2019/04/18
     *
     * solution：
     * 1. 依然使用递归的先序遍历 然后这里不是加val而是x += 1即可  time:O(n), space: O(n)
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int[] res = new int[]{Integer.MAX_VALUE};
        int cur = 0;
        helper(root, res, cur);
        return res[0];
    }

    /**
     * 考虑到返回值的不好，所以使用void
     * 这里计算的是最小层数 不是最小的sum
     * @param root
     * @param res
     * @param cur
     */
    public static void helper(TreeNode root, int[] res, int cur) {
        if (root == null) return;
        cur += 1;
        if (root.left == null && root.right == null) {
            res[0] = Math.min(res[0], cur);
        }
        helper(root.left, res, cur);
        helper(root.right, res, cur);
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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        // 这一步需要好好的思考其过程 （最后也有+1 所以之前的值 就会相应的少1 ，并且需要max是因为只要求存在子节点的那一部分而不是为空的部分）
        if (root.left == null || root.right == null) {
            return Math.max(minDepth(root.left), minDepth(root.right)) + 1;
        }
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }
}
