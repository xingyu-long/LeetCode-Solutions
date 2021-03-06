package com.leetcode.linkedList;

public class _1472_DesignBrowserHistory {
    class ListNode {
        ListNode prev;
        ListNode next;
        String val;

        public ListNode(String v) {
            val = v;
        }
    }

    ListNode curr;

    public _1472_DesignBrowserHistory(String homepage) {
        curr = new ListNode(homepage);
    }

    public void visit(String url) {
        ListNode newNode = new ListNode(url);
        curr.next = newNode;
        newNode.prev = curr;
        curr = newNode;
    }

    public String back(int steps) {
        while (curr.prev != null && steps-- > 0) {
            curr = curr.prev;
        }
        return curr.val;
    }

    public String forward(int steps) {
        while (curr.next != null && steps-- > 0) {
            curr = curr.next;
        }
        return curr.val;
    }
}
