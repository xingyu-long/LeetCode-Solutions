package com.leetcode.Design;

/**
 * @Date: 06/07/2020
 * @Description: Design, Array of LinkedList
 **/
public class _705_DesignHashSet {

    class Node {

        Node next;
        int key;

        public Node(int key) {
            this.key = key;
        }
    }

    private Node[] list;

    /**
     * Initialize your data structure here.
     */
    public _705_DesignHashSet() {
        list = new Node[10000 + 1];
    }

    // 重复了呢？？？？
    public void add(int key) {
        int index = getIndex(key);
        if (list[index] == null) {
            list[index] = new Node(-1);
        }
        Node prev = find(list[index], key);
        if (prev.next == null) {
            prev.next = new Node(key);
        } // 如果有相同的，我们就不管
    }

    public void remove(int key) {
        int index = getIndex(key);
        if (list[index] == null) {
            return;
        }
        Node prev = find(list[index], key);
        if (prev.next == null) {
            return;
        } else {
            prev.next = prev.next.next;
        }
    }

    /**
     * Returns true if this set contains the specified element
     */
    public boolean contains(int key) {
        int index = getIndex(key);
        if (list[index] == null) {
            return false;
        }
        Node prev = find(list[index], key);
        if (prev.next == null) {
            return false;
        }
        return true;
    }

    public Node find(Node bucket, int key) {
        Node curr = bucket, prev = null;
        while (curr != null && curr.key != key) {
            prev = curr;
            curr = curr.next;
        }
        return prev;
    }

    public int getIndex(int key) {
        return Integer.hashCode(key) % list.length;
    }
}
