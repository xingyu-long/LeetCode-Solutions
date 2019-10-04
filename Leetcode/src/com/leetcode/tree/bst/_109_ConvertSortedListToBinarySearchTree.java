package com.leetcode.tree.bst;


import com.leetcode.common.ListNode;
import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class _109_ConvertSortedListToBinarySearchTree {

    /**
     * 109. Convert Sorted List to Binary Search Tree
     * When: 2019/04/25
     *
     * solution:
     * 将其转化为array 然后使用108的方法
     *
     * Time: O(n)
     * space: O(n)
     *
     *
     * @param head
     * @return
     */
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> values = new ArrayList<>();
        while (head != null) {
            values.add(head.val);
            head = head.next;
        }
        return helper(values, 0, values.size()-1);
    }

    public TreeNode helper(List<Integer> values, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(values.get(mid));
        node.left = helper(values, start, mid - 1);
        node.right = helper(values, mid + 1, end);
        return node;
    }

    /**
     * solution2： 利用和array一样的思想，只是利用链表循环得到mid
     * 设置三个指针，slow（每次走一步） fast（每次走两步）
     * 这样当fast走到尽头的时候，slow刚好到中间然后保留，然后左右分开，slow / slow.next
     *
     * Test case:
     * -10 -> -3 -> 0 -> 5 -> 9
     * head
     * slow
     * fast
     * (1)
     *    slow.val = 0
     * (2)
     *   left(-10, 0)
     *   -10 -> -3 -> 0
     *   head
     *                tail
     *   slow-->slow
     *   fast ------->fast
     *   (2.1) slow.val = -3
     *         left (-10, -3)
     *             slow.val = -10;
     *             (2.1.1) left (-10, -10) return null
     *             (2.1.2) right (-3, -3) return null;
     *         right (-3, -3) return null;
     * (3)
     *  right(5, null)
     *     5 -> 9 -> null
     *     head
     *               tail
     *     slow->slow
     *     fast-----> fast
     *     (3.1) slow.val = 9;
     *          left (5, 9)
     *              slow.val = 5;
     *              (3.1.1) left(5,5) return null;
     *              (3.1.2) right(9,9) return null;
     *          right (null, null) return null;
     *
     *            0
     *           / \
     *         -3   9
     *         /   /
     *      -10   5
     *
     * time: O(log^N)
     * space: O(n) 链表
     * @param head
     * @return
     */
    public static TreeNode sortedListToBST2(ListNode head) {
        if (head == null) return null;
        return toBST(head, null);
    }

    public static TreeNode toBST(ListNode head, ListNode tail) {
        if (head == tail) return null; //这里用的很妙
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != tail && fast.next.next != tail) {
            fast = fast.next.next;
            slow = slow.next;
        }
        TreeNode root = new TreeNode(slow.val);
        root.left = toBST(head, slow); // 这里这里没有用prev
        root.right = toBST(slow.next, tail);
        return root;
    }
}
