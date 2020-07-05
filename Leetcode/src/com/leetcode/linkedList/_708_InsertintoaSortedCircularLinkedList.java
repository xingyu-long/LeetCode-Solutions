package com.leetcode.linkedList;

/**
 * @Date: 05/30/2020
 * @Description: LinkedList
 **/
public class _708_InsertintoaSortedCircularLinkedList {

    class Node {

        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }

    public Node insert(Node head, int insertVal) {
        if (head == null) {
            head = new Node(insertVal, null);
            head.next = head;
            return head;
        }
        Node prev = head;
        Node curr = prev.next;
        while (curr != head) {
            // a <= insert <= b
            if (insertVal >= prev.val && insertVal <= curr.val) {
                break;
            }
            // 4->1->3 insert:0 or 5 这里需要严格的大于，这样是有一个转折带你
            if (prev.val > curr.val && (insertVal >= prev.val || insertVal <= curr.val)) {
                break;
            }
            prev = curr;
            curr = curr.next;
        }
        prev.next = new Node(insertVal, curr);
        return head;
    }
}
