package com.leetcode.tree;

import com.leetcode.lib.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _199_BinaryTreeRightSideView {

    /**
     * 199. Binary Tree Right Side View
     * When: 2019/04/22
     *
     * solution: 利用level+类似于preOrder的方式
     * 这里的level == res.size()才插入就说明了其完成右边看 （左边的深度更深，所以从右边看也能看见）
     *
     * test case:
     * level:
     *  0               A
     *                 / \
     *  1             B  C
     *                \
     *  2              D
     *
     *  res = [] level = 0
     *  (1) res.size()== 0
     *          res -> [A,]
     *  (2) right:
     *          res.size() == level (1)
     *          res -> [A, C, ] 后面进去就没有插入并且由于其左右为null 则直接return;
     *  (3) left
     *         res.size(2) != level(1)
     *         (3.1) right:
     *               res.size(2) == level (2)
     *               res -> [A, C, D]
     *         (3.2) left:
     *               root == null
     *               return;
     *  return res[A, C, D]
     * @param root
     * @return
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, 0);
        return res;
    }

    public void helper(List<Integer> res, TreeNode root, int level) {
        if (root == null) return;
        if (res.size() == level) {
            res.add(root.val);
        }
        helper(res, root.right, level + 1);
        helper(res, root.left, level + 1);
    }
}
