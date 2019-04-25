package com.leetcode.tree;

import com.leetcode.lib.Node;

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
