/*
 * @Date: 08/11/2020 16:07:14
 * @LastEditTime: 07/30/2022 13:34:51
 * @Description: Doubly LinkedList
 */
package com.leetcode.linkedList;

import java.util.Stack;

public class _430_FlattenaMultilevelDoublyLinkedList {

    public class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;

        public Node() {
        }

        public Node(int _val, Node _prev, Node _next, Node _child) {
            val = _val;
            prev = _prev;
            next = _next;
            child = _child;
        }
    }

    // 都需要考虑到cur.next是否为空，如果为空，后面就不需要连接
    // time: O(n) space: O(1)
    public Node flatten(Node head) {
        if (head == null)
            return null;

        Node curr = head;
        while (curr != null) {
            if (curr != null && curr.child != null) {
                Node seconLevel = curr.child;
                Node seconLevelEnd = seconLevel;
                while (seconLevelEnd.next != null) {
                    seconLevelEnd = seconLevelEnd.next;
                }

                Node next = curr.next;

                curr.next = seconLevel;
                seconLevel.prev = curr;
                curr.child = null;

                seconLevelEnd.next = next;
                if (next != null) {
                    next.prev = seconLevelEnd;
                }
            }
            curr = curr.next;
        }
        return head;
    }

    // 利用stack记录
    public Node flatten2(Node head) {
        if (head == null)
            return null;
        Node curt = head;
        Stack<Node> stack = new Stack<>(); // store curt.next when curt.child is not null

        while (curt != null) {
            if (curt.child != null) {
                stack.push(curt.next); // might be null
                curt.next = curt.child;
                curt.next.prev = curt;
                curt.child = null;
            } else if (curt.next == null && !stack.isEmpty()) { // reach of tail of child, reconnect the next of parent
                curt.next = stack.pop();
                if (curt.next != null)
                    curt.next.prev = curt;
            }

            curt = curt.next;
        }

        return head;
    }

    // 自己写的stack方法
    public Node flatten3(Node head) {
        if (head == null)
            return head;
        Stack<Node> stack = new Stack<>();
        Node curr = head, prev = null;
        while (curr != null) {
            if (curr.child != null) {
                if (curr.next != null)
                    stack.push(curr.next);
                Node child = curr.child;
                curr.child = null;
                curr.next = child;
                child.prev = curr;
            }
            prev = curr;
            curr = curr.next;
        }
        // print(head);
        while (!stack.isEmpty()) {
            Node peek = stack.peek();
            prev.next = peek;
            peek.prev = prev;
            stack.pop();
            while (prev.next != null) {
                prev = prev.next;
            }
        }
        return head;
    }
}
