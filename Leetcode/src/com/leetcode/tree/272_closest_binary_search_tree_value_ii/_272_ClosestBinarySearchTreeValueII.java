/*
 * @Date: 2019-11-15 21:15:05
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 07/21/2022 10:43:31
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class _272_ClosestBinarySearchTreeValueII {

    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Deque<Integer> deque = new LinkedList<>();
        dfs(root, target, k, deque);
        return new ArrayList<>(deque);
    }
    
    public void dfs(TreeNode root, double target, int k, Deque<Integer> deque) {
        if (root == null) {
            return;
        }
        
        dfs(root.left, target, k, deque);
        if (deque.size() == k) {
            if (Math.abs(root.val - target) < Math.abs(deque.peekFirst() - target)) {
                deque.pollFirst();
            } else {
                return;
                // 因为是用中序遍历，后面的只会越来越大，所以直接结束
            }
        }
        deque.offerLast(root.val);
        
        dfs(root.right, target, k, deque);
    }
}
