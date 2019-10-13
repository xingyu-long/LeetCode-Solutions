package com.leetcode.linkedList;

public class _430_FlattenaMultilevelDoublyLinkedList {

    public class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {}

        public Node(int _val,Node _prev,Node _next,Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    /**
     * 430. Flatten a Multilevel Doubly Linked List
     * When:2019/10/11
     * Difficulty: Medium
     * 注意通过找到一个child然后就和上一层合并，然后再找。
     * @param head
     * @return
     */
    public Node flatten(Node head) {
        // 相当于每一层先计算一层一层连接
        if (head == null) return null;
        Node cur = head;
        while (cur != null) {
            // find the node that contains child
            if (cur.child == null){
                cur = cur.next;
                continue;
            }
            Node temp = cur.child;
            while (temp.next != null) {
                temp = temp.next;
            }
            // child那一层的最后一个节点连接到发现child节点的（其实就是上一层）后一个
            temp.next = cur.next;
            if (cur.next != null) cur.next.prev = temp;
            cur.next = cur.child;
            cur.child.prev = cur;
            cur.child = null;
        }
        return head;
    }
}
