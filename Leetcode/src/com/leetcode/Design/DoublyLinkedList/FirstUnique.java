package com.leetcode.design.DoublyLinkedList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Date: 04/28/2020
 * @Description: 四月每天打卡题目, doubly LinkedList
 **/
//https://leetcode.com/explore/challenge/card/30-day-leetcoding-challenge/531/week-4/3313/
public class FirstUnique {

    // 更好的实现，相当于一直poll出重复的就行
    Map<Integer, Integer> freq = new HashMap<>();
    Queue<Integer> q = new LinkedList<>();

    public FirstUnique(int[] nums) {
        for (int x : nums) {
            add(x);
        }
    }

    public int showFirstUnique() {
        while (!q.isEmpty() && freq.get(q.peek()) > 1) {
            q.poll();
        }
        return q.isEmpty() ? -1 : q.peek();
    }

    public void add(int value) {
        freq.put(value, freq.getOrDefault(value, 0) + 1);
        q.offer(value);
    }

    // 这是自己写的版本
    class DNode {

        DNode prev;
        DNode next;
        int val;
    }

    Map<Integer, DNode> map;
    Map<Integer, Integer> count;
    // 利用双向链表？？？
    DNode head;
    DNode tail;

//    public FirstUnique(int[] nums) {
//        map = new HashMap<>();
//        count = new HashMap<>(); // 忘记了
//        head = new DNode();
//        tail = new DNode();
//
//        head.prev = null;
//        head.next = tail;
//
//        tail.next = null;
//        tail.prev = head;
//        for (int num : nums) {
//            count.put(num, count.getOrDefault(num, 0) + 1);
//        }
//        for (int num : nums) { // 需要按照原来的顺序读
//            if (count.get(num) == 1) addToList(num);
//        }
//        // traversal();
//    }

    public int showFirstUnique2() {
        if (head.next == tail) {
            return -1; // 没有unique存在
        } else {
            return head.next.val;
        }
    }

    public void add2(int value) {
        count.put(value, count.getOrDefault(value, 0) + 1);
        if (count.get(value) == 1) {
            addToList(value);
        } else {
            if (map.containsKey(value)) {
                remove(value);
            }
        }
    }


    public void remove(int val) {
        DNode node = map.get(val);
        System.out.println("remove - " + node.val);
        map.remove(val);
        removeByDNode(node);
    }

    // for the doubly linked list
    public void removeByDNode(DNode node) {
        // 同样需要更新map
        DNode before = node.prev;
        DNode after = node.next;

        before.next = after;
        after.prev = before;
    }

    //
    public void addToList(int val) {
        // 需要更新map
        DNode beforeTail = tail.prev;
        DNode newNode = new DNode();
        newNode.val = val;
        newNode.next = tail;
        tail.prev = newNode;
        newNode.prev = beforeTail;
        beforeTail.next = newNode;
        map.put(val, newNode);
    }

    public void traversal() {
        for (DNode i = head.next; i != tail; i = i.next) {
            System.out.println(i.val);
        }
    }

    // 利用map.putIfAbsent()是不是有这个对应
    /**
     private static final int mi = Integer.MIN_VALUE, mx = Integer.MAX_VALUE; // dummy values of head and tail.
     private static final DoublyLinkedList head = new DoublyLinkedList(mi),
     tail = new DoublyLinkedList(mx);
     private static class DoublyLinkedList {

     public DoublyLinkedList prev, next;
     public int val;

     public DoublyLinkedList(int v) {
     val = v;
     }

     }

     private final Map<Integer, DoublyLinkedList> intToNode = new HashMap<>(); // map values to nodes.

     public FirstUnique(int[] nums) {
     head.next = tail; // construct empty list: head connects tail.
     tail.prev = head; // construct empty list: tail connects head.
     for (int num : nums) { // add all numbers to doubly linked list and corresponding entries to HashMap.
     add(num);
     }
     }

     public int showFirstUnique() {
     return head.next.val == mx ? -1 : head.next.val; // return -1 if empty list; or the first unique value.
     }

     public void add(int value) {
     DoublyLinkedList node = intToNode.putIfAbsent(value, new DoublyLinkedList(value));
     if (node != null) { // HashMap intToNode already contains entry (value=node).
     remove(node); // value is NOT unique, hence remove it from the doubly linked list.
     }else { // HashMap intToNode does NOT contains key value.
     putToEnd(intToNode.get(value)); // value is unique, hence put it to the end of the doubly linked list.
     }
     }

     private boolean remove(DoublyLinkedList node) {
     if (node.prev == null || node.next == null) { // node NOT in the list.
     return false; // remove operation failed.
     }
     node.prev.next = node.next; // modify next pointer of the previous node.
     node.next.prev = node.prev; // modify previous pointer of the next node.
     node.prev = null; // cut off the previous pointer from node to list.
     node.next = null; // cut off the next pointer from node to list.
     return true; // remove operation succeeded.
     }

     private boolean putToEnd(DoublyLinkedList node) {
     if (tail == null || tail.prev == null) { // tail node error.
     return false; // operation failed.
     }
     node.next = tail; // assign the tail to the next pointer of node.
     node.prev = tail.prev; // assign the previous node of the tail to the next pointer of node.
     tail.prev = node; // modify previous pointer of the tail.
     node.prev.next = node; // modify next pointer of the previous node.
     return true; // operation succeeded.
     }
     * */
}
