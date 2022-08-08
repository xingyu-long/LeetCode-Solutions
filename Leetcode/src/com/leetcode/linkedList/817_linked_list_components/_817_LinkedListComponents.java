package com.leetcode.linkedList;

import com.leetcode.common.ListNode;

import java.util.HashSet;
import java.util.Set;

public class _817_LinkedListComponents {

    // 有点像那个移动consecutive number
    public static int numComponents(ListNode head, int[] G) {
        int res = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int num : G) set.add(num);
        ListNode cur = head;
        while (cur != null) {
            if (!set.contains(cur.val)) {
                cur = cur.next;
                continue;
            }
            while (cur != null && set.contains(cur.val)) {
                set.remove(cur.val);
                cur = cur.next;
            }
            res++;
        }
        return res;
    }

    // 思路基本一致。只是更加elegant
    public int numComponents2(ListNode head, int[] G) {
        Set<Integer> setG = new HashSet<>();
        for (int i: G) setG.add(i);
        int res = 0;
        while (head != null) {
            if (setG.contains(head.val) && (head.next == null || !setG.contains(head.next.val))) res++;
            head = head.next;
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        head.next = new ListNode(1);
        head.next.next = new ListNode(2);
        head.next.next.next = new ListNode(3);
        int[] nums = new int[]{0,1,2,3};
        System.out.println(numComponents(head, nums));
    }
}
