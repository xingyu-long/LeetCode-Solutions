package com.leetcode.linkedList;

public class _707_DesignLinkedList {

    public class DNode {
        DNode next;
        DNode prev;
        int val;

        public DNode(int val) {
            this.val = val;
        }

        public DNode() {}
    }
    // doubly list
    DNode head;
    DNode tail;
    int total;

    /** Initialize your data structure here. */
    public _707_DesignLinkedList() {
        total = 0;
        head = new DNode();
        head.prev = null;

        tail = new DNode();
        tail.next = null;

        head.next = tail;
        tail.prev = head;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */ // O(n)
    public int get(int index) {
        if (index >= total) return -1;
        DNode cur = head.next; // head和tail节点是dummy node
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        DNode node = new DNode(val);
        DNode next = head.next;

        head.next = node;
        node.next = next;

        node.prev = head;
        next.prev = node;
        total++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        DNode node = new DNode(val);
        DNode pre = tail.prev;

        tail.prev = node;
        node.next = tail;

        pre.next = node;
        node.prev = pre;
        total++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index > total) return;
        DNode node = new DNode(val);
        DNode cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        DNode temp = cur.next;
        cur.next = node;
        node.next = temp;

        node.prev = cur;
        temp.prev = node;
        total++;
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index >= total) return;
        DNode cur = head.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        DNode before = cur.prev;
        DNode after = cur.next;

        before.next = after;
        after.prev = before;
        total--;
    }
}
