package com.leetcode.Design.DoublyLinkedList;

import java.util.HashMap;

public class _146_LRUCache {

    /**
     * 146. LRU Cache
     * When:2019/7/6
     * Design and implement a data structure for Least Recently Used (LRU) cache.
     * It should support the following operations: get and put.

     get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
     put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity,
     it should invalidate the least recently used item before inserting a new item.

     Follow up:
     Could you do both operations in O(1) time complexity?

     Example:

     _146_LRUCache cache = new _146_LRUCache( 2  capacity  );

     cache.put(1, 1);
     cache.put(2, 2);
     cache.get(1);       // returns 1
     cache.put(3, 3);    // evicts key 2
     cache.get(2);       // returns -1 (not found)
     cache.put(4, 4);    // evicts key 1
     cache.get(1);       // returns -1 (not found)
     cache.get(3);       // returns 3
     cache.get(4);       // returns 4

     HashMap + Double Linked List
     */
    //time:O(1) space:O(n)
    private class DNode {
        int key;
        int value;
        DNode prev;
        DNode next;
    }

    private HashMap<Integer, DNode> map = new HashMap<>();
    private DNode head, tail;
    private int totalItemsCache;
    private int maxCapacity;

    public _146_LRUCache(int capacity) {
        totalItemsCache = 0;
        this.maxCapacity = capacity;

        // Initialize the dummy head and tail of the cache;
        head = new DNode();
        head.prev = null;

        tail = new DNode();
        tail.next = null;

        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DNode node = map.get(key);
        boolean itemFoundInCache = node != null;

        if (!itemFoundInCache) {
            return -1;
        }

        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        //如果没有这个，就插入（离head最近）
        DNode node = map.get(key);
        boolean itemFoundInCache = node != null;

        if (!itemFoundInCache) {
            DNode newNode = new DNode();
            newNode.key = key;
            newNode.value = value;

            map.put(key, newNode);
            addNode(newNode);

            totalItemsCache++;

            if (totalItemsCache > maxCapacity) {
                removeLRUEntry();
            }

        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    /**
        [head] ->  [tail]
               <-

        [head] ->  [newNode1] -> [tail]
               <-            <-

        [head] ->  [newNode2] -> [newNode1] -> [tail]
               <-             <-            <-
     */
    /** important operation */
    private void addNode(DNode node) {
        node.prev = head;
        node.next = head.next;

        head.next.prev = node;
        head.next = node;
    }

    private void removeLRUEntry() {
        DNode remove = popTail();
        map.remove(remove.key);
        --totalItemsCache;
    }

    private DNode popTail() {
        DNode itemBeingRemoved = tail.prev;
        removeNode(itemBeingRemoved);
        return itemBeingRemoved;
    }
    /** important operation */
    // 相当于直接跳过，先保存后面节点的情况，然后
    private void removeNode(DNode node) {
        DNode savedPrev = node.prev;
        DNode savedNext = node.next;

        savedPrev.next = savedNext;
        savedNext.prev = savedPrev;
    }
    private void moveToHead(DNode node) {
        removeNode(node);
        addNode(node);
    }
}
