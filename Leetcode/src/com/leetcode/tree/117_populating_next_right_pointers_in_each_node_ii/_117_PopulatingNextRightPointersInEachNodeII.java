package com.leetcode.tree;

import com.leetcode.common.Node;

import java.util.LinkedList;
import java.util.Queue;

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
        // 表示如果存在prev，就直接相连，如果没有的话，就把当前的赋值给他。也要注意head的赋值
        Node nextHead = null; // 每一层的开头
        Node prev = null; // 同一层的prev节点，只要存在，就马上和左边或者右边不为空的部分连接并且移动prev
        Node cur = root;

        while (cur != null) { // 多少层
            while (cur != null) {  // 每一层的操作
                if (cur.left != null) {
                    if (prev != null) {
                        prev.next = cur.left;
                    } else {
                        nextHead = cur.left;
                    }
                    prev = cur.left;
                }
                if (cur.right != null) {
                    if (prev != null) {
                        prev.next = cur.right;
                    } else {
                        nextHead = cur.right;
                    }
                    prev = cur.right;
                }
                cur = cur.next;
            }
            // 如果到了最后一层，最后cur被head赋值，head为null，所以退出。x
            cur = nextHead;
            prev = null;
            nextHead = null;
        }
        return root;
    }


    // 利用queue做，这个就需要一些额外的空间了，还是看上面最优的解法
    // 通用解
    public Node connect2(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (i < size - 1) { // 在最后一个节点之前的所有情况
                    cur.next = queue.peek();
                }
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return root;
    }
}
