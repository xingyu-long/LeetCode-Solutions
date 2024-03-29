package com.leetcode.ood;

class _706_DesignHashMap {

    ListNode[] nodes;
    // 利用拉链法

    /**
     * Initialize your data structure here.
     */
    public _706_DesignHashMap() {
        nodes = new ListNode[10000];
    }

    /**
     * value will always be non-negative.
     */
    // 这里之前写错了。。。
    public void put(int key, int value) {
        int idx = getIndex(key);
        // 如果是bucket 赋值就错误了
        if (nodes[idx] == null) { // 第一个都是初始化为 -1, -1
            nodes[idx] = new ListNode(-1, -1);
        }
        ListNode prev = find(nodes[idx], key);
        if (prev.next == null) { // 这里是prev.next;
            prev.next = new ListNode(key, value);
        } else {
            prev.next.value = value;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping
     * for the key
     */
    public int get(int key) {
        int idx = getIndex(key);
        if (nodes[idx] == null) {
            return -1;
        }
        ListNode prev = find(nodes[idx], key);
        if (prev.next == null) {
            return -1;
        } else {
            return prev.next.value;
        }
    }

    /**
     * Removes the mapping of the specified value key if this map contains a mapping for the key
     */
    public void remove(int key) {
        int idx = getIndex(key);
        if (nodes[idx] == null) {
            return;
        }
        ListNode prev = find(nodes[idx], key);
        if (prev.next == null) {
            return;
        } else {
            prev.next = prev.next.next;
        }
    }

    public int getIndex(int key) {
        return Integer.hashCode(key) % nodes.length;
    }

    // 相当于是在找那个bucket里面的能够对应上key的地方
    // 如果没有的话，就是返回bucket这个的位置。
    public ListNode find(ListNode bucket, int key) {
        ListNode node = bucket, prev = null;
        while (node != null && key != node.key) {
            prev = node;
            node = node.next;
        }
        return prev;
    }


    class ListNode {

        int key;
        int value;
        ListNode next;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public static void main(String[] args) {
        _706_DesignHashMap hashMap = new _706_DesignHashMap();
        hashMap.put(1, 1);
        System.out.println(hashMap.get(1));
    }
}
