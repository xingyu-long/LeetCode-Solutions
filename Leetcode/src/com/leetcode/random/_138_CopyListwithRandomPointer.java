package com.leetcode.random;

import com.leetcode.common.ListNode;

import java.util.HashMap;

public class _138_CopyListwithRandomPointer {

    /**
     * 138. Copy List with Random Pointer
     * When: 2019/06/27
     * Review1: 2019/7/7
     * review1: 11/7/2019
     * Difficulty: Medium
     * <p>
     * solution: 使用HashMap进行辅助
     */
    // https://www.youtube.com/watch?v=OvpKeraoxW0
    private static class Node {
        int val;
        Node next;
        Node random;

        Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }

        Node(int val) {
            this.val = val;
        }
    }

    // time: O(n) space: O(n) 这里用hashmap找出了关系！
    public static Node copyRandomList(Node head) {
        // 先使用HashMap复制保存，然后将其中的关系再连起来
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        // 先保存当前的 这个链表
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        //使用map连起来，并且返回head (这里是map的value值返回)
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    //time:O(n) space:O(1)
    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        // first pass, keep copy pointers.
        Node cur = head;
        Node next = null;
        while (cur != null) {
            Node copy = new Node(cur.val);
            next = cur.next;
            cur.next = copy;
            copy.next = next;

            // advanced cur
            cur = next;
        }

        // second pass. copy random pointer
        cur = head;
        while (cur != null) {
            if (cur.random != null) {
                cur.next.random = cur.random.next;
            }
            cur = cur.next.next;
        }
        // third pass. restore the original list and connect the copy nodes.
        Node dummy = new Node(0);
        Node tail = dummy;
        Node copy = null;
        cur = head;
        while (cur != null) {
            next = cur.next.next;

            copy = cur.next;

            tail.next = copy;
            tail = tail.next;

            cur.next = next;
            cur = next;
        }
        return dummy.next;
    }
    // 这个是复制带有next的node
    public static ListNode copyList(ListNode node) {
        ListNode newHead = null;
        ListNode tail = null;
        ListNode cur = node;
        while (cur != null) {
            if (newHead == null) {
                newHead = new ListNode(cur.val);
                tail = newHead;
            } else {
                tail.next = new ListNode(cur.val);
                tail = tail.next;
            }
            cur = cur.next;
        }
        return newHead;
    }

    public static void main(String[] args) {
//        ListNode root = new ListNode(0);
//        root.next = new ListNode(1);
//        root.next.next = new ListNode(2);
//        ListNode copy = copyList(root);
//        while (copy != null) {
//            System.out.println(copy.val);
//            copy = copy.next;
//        }
        Node node0 = new Node(0);
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        node0.next = node1;
        node1.next = node2;
        node2.next = node3;
        node0.random = node2;
        node1.random = node3;
        node2.random = node0;
        copyRandomList(node0);
    }
}
