package com.leetcode.random;

import java.util.HashMap;

public class _138_CopyListwithRandomPointer {

    /**
     *  138. Copy List with Random Pointer
        When: 2019/06/27
        Review1: 2019/7/7
        Difficulty: Medium

        solution: 使用HashMap进行辅助
    */
    //time:O(n) space:O(n)
    private class Node {
        int val;
        Node next;
        Node random;

        Node(int val, Node next, Node random) {
            this.val = val;
            this.next = next;
            this.random = random;
        }
    }
    // time: O(n) space: O(n)
    public Node copyRandomList(Node head) {
        // 先使用HashMap复制保存，然后将其中的关系再连起来
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        // 先保存当前的 这个链表
        while (cur != null) {
            map.put(cur, new Node(cur.val, cur.next, cur.random));
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
}
