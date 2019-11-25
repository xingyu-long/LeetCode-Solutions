package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class _100_SameTree {

    /**
     * 100. Same Tree
     * When: 2019/04/15
     * Review1:2019/7/25
     * review2:2019/9/11
     * <p>
     * solution:
     * 递归两棵树的左边全部和右边全部
     *
     * @param p
     * @param q
     * @return
     */
    // time:O(n) space: best O(lgN) worse O(n)
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true; // 主要依靠这个返回
        // 需要注意这个
        if (p == null || q == null) return false;
        if (p.val != q.val) return false; // 之前写错成了 p == q return true
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    // BFS 记得每次用check函数验证left right是否合格
    // time:O(n) space:O(n)
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        Queue<TreeNode> queueP = new LinkedList<>();
        Queue<TreeNode> queueQ = new LinkedList<>();
        queueP.offer(p);
        queueQ.offer(q);
        while (!queueP.isEmpty()) {
            TreeNode nodeP = queueP.poll();
            TreeNode nodeQ = queueQ.poll();

            if (!(check(nodeP, nodeQ))) return false;
            if (nodeP != null && nodeQ != null) {
                if (!(check(nodeP.left, nodeQ.left))) return false;
                if (nodeP.left != null) {
                    queueP.offer(nodeP.left);
                    queueQ.offer(nodeQ.left);
                }
                if (!(check(nodeP.right, nodeQ.right))) return false;
                if (nodeP.right != null) {
                    queueP.offer(nodeP.right);
                    queueQ.offer(nodeQ.right);
                }
            }
        }
        return true;
    }

    public boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return true;
    }

    // 这个更加简单易懂一些！
    public boolean isSameTree3(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            TreeNode t1 = queue.poll();
            TreeNode t2 = queue.poll();
            if (t1 == null && t2 == null) continue; // 这里很重要，因为后面可能有违反啊
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            queue.offer(t1.left);
            queue.offer(t2.left);
            queue.offer(t1.right);
            queue.offer(t2.right);
        }
        return true;
    }
}