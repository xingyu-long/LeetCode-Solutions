/*
 * @Date: 2019-09-20 17:02:42
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-25 13:34:23
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _199_BinaryTreeRightSideView {
    // solution: 利用level+类似于preOrder的方式 终于自己写出来了！！！！
    // 这里的level == res.size()才插入就说明了其完成右边看 （左边的深度更深，所以从右边看也能看见）
    // time: O(depth) depth
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, root, 0);
        return res;
    }

    // 就是每一层所在level等于其结果size才加入 所以第0层加入了一个，第一层加入了一个。。。。
    public void helper(List<Integer> res, TreeNode root, int level) {
        if (root == null) return;
        if (res.size() == level) {
            res.add(root.val);
        }
        helper(res, root.right, level + 1);
        helper(res, root.left, level + 1);
    }

    // BFS solution
    // 反着来的BFS（先加入右子树）这样的第一个节点都是rightView
    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) res.add(cur.val);
                if (cur.right != null) queue.offer(cur.right);
                if (cur.left != null) queue.offer(cur.left);
            }
        }
        return res;
    }
}
