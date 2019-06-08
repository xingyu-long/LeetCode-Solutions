package com.leetcode.tree;

import com.leetcode.common.Node;

public class _117_PopulatingNextRightPointersInEachNodeII {

    /**
     * 117. Populating Next Right Pointers in Each Node II
     * When: 2019/04/26
     *
     * solution：这个要比第一种情况要难一些 多一个pre指针
     *
     * test case:
     *         1
     *        / \
     *       2   3
     *      /    \
     *     4      5
     *
     * (1) head, pre = null, cur = node(1)
     *      head, pre = node(2)
     *      由于第二个if中的if成立 则就是pre.next = cur.right 即是 2->3
     *      (1.1) cur = node(1).next = null
     *      跳出循环
     * (2) head, pre = null, cur = 以前的head node(2)
     *      head,pre = node(2).left node(4)
     *      (2.1) cur = node(2).next = node(3)
     *            由于pre!=null 所以node(4) 连接 node(5) 4->5
     *      跳出循环
     * (3) head, pre = null, cur = 以前的head node(4)
     *      由于没有左右子树 只能cur = cur.next -> node(5)
     *      然后再next 跳出循环
     * (4) cur = head 这里就是null 程序结束
     *
     * pre, head 保持是cur.left的位置或者为null
     * cur 则会 逐层一个个节点遍历
     *
     * Time: O(n) 因为实际只是从头到尾的遍历
     * space: O(1)
     * @param root
     * @return
     */
    public Node connect(Node root) {
        Node head = null;
        Node pre = null;
        Node cur = root;

        while (cur != null) {
            while (cur != null) {
                if (cur.left != null) {
                    if (pre != null) {
                        pre.next = cur.left;
                    } else {
                        head = cur.left;
                    }
                    pre = cur.left;
                }
                if (cur.right != null) {
                    if (pre != null) {
                        pre.next = cur.right;
                    } else {
                        head = cur.right;
                    }
                    pre = cur.right;
                }
                cur = cur.next;
            }
            cur = head;
            pre = null;
            head = null;
        }
        return root;
    }
}
