package com.leetcode.linkedList;

import com.leetcode.common.Node;
import java.util.Stack;

/**
 * @Date: 05/20/2020
 * @Description: LinkedList
 **/
public class _426_ConvertBinarySearchTreetoSortedDoublyLinkedList {

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        // morris-traversal???
        Node prev = null, first = null, end = null;
        Stack<Node> stack = new Stack<>();
        Node curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            end = curr;
            if (prev != null) {
                //connect?
                curr.left = prev;
                prev.right = curr;
            }

            if (first == null) {
                first = curr;
            }
            prev = curr;
            curr = curr.right;
        }

        first.left = end;
        end.right = first;
        return first;
    }
}
