/*
 * @Date: 2020-01-23 14:39:38
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-28 14:47:24
 */
package com.leetcode.tree.bst;

public class _510_InorderSuccessorinBSTII {
    // 判断当前节点是否有right child。
    // https://www.cnblogs.com/grandyang/p/10424982.html
    static class Node {

        int val;
        Node left;
        Node right;
        Node parent;

        Node(int val) {
            this.val = val;
            left = null;
            right = null;
            parent = null;
        }
    }

    // 利用了值比较的解法，这样第二种情况的时候直接找第一个大于node的值。
    Node findSuccessor(Node node) {
        Node res = null;
        if (node.right != null) {
            // find the left most;
            res = node.right;
            while (res != null && res.left != null) res = res.left;
        } else {
            // go to the parent node and compare the value.
            res = node.parent;
            while (res != null && res.val < node.val) res = res.parent;
        }
        return res;
    }

    // 不能比较其值，这样在第二种情况的时候就需要向上的过程中确定child从right过来的。
    // 利用左右的关系可以决定
    Node findSuccessor2(Node node) {
        if (node.right != null) {
            node = node.right;
            while (node != null && node.left != null) node = node.left;
            return node;
        } else {
            Node ancestor = node.parent;
            Node curr = node;
            while (ancestor != null && ancestor.right == curr) {
                curr = ancestor;
                ancestor = curr.parent;
            }
            return ancestor;
        }
    }
}
