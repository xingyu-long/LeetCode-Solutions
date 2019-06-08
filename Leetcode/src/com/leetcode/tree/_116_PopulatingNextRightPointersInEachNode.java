package com.leetcode.tree;

import com.leetcode.common.Node;

public class _116_PopulatingNextRightPointersInEachNode {

    /**
     * 116. Populating Next Right Pointers in Each Node
     * When: 2019/04/25
     *
     * solution:
     * 首先连接左右子节点，然后连接相邻。
     *
     * test case:
     *          1 ->null
     *        /   \
     *       2 --->3 -> null
     *     /  \   /  \
     *    4 -->5->6->7 -> null
     *
     *    内部连接那一块就主要看2 这个点
     *    只要2.right!=null
     *    2.next!=null
     *    所以就可以把 2的右边和3的左边连接起来
     *    2.right.next = 2.next.left
     * @param root
     * @return
     */
    public Node connect(Node root) {
        if (root == null) return null;
        if (root.left != null) {
            root.left.next = root.right;
        }
        if (root.right != null && root.next != null) {
            root.right.next = root.next.left;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }

    /**
     * Test case:
     *               1
     *              / \
     *             2   3
     *            / \ / \
     *           4  5 6  7
     *  start, cur:  node (1)
     *  （1）进入cur的while循环 连接了 2和 3
     *       cur = null 退出循环
     *  （2）start = node(1).left, cur: node(2)
     *       连接4->5以及5->6
     *       cur = node(2).next node(3)
     *       连接6->7
     *       cur = null
     *  （3）然后到树的最底层 然后cur一直可以next 最后为null 结束
     * @param root
     * @return
     */
    public Node connect2(Node root) {
        Node start = root;
        while (start != null) {
            Node cur = start;
            while (cur != null) {
                if (cur.left != null) {
                    cur.left.next = cur.right;
                }
                if (cur.right != null && cur.next != null) {
                    cur.right.next = cur.next.left;
                }
                cur = cur.next;
            }
            start = start.left;
        }
        return root;
    }
}
