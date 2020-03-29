/*
 * @Date: 2019-11-15 21:15:05
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-28 17:03:44
 */
package com.leetcode.tree;

import com.leetcode.common.ConverterForTreeAndString;
import com.leetcode.common.TreeNode;

import java.util.LinkedList;
import java.util.List;

public class _272_ClosestBinarySearchTreeValueII {

    // 利用 PriorityQueue 来维护 K 个接近的节点 也可以用 LinkedList 来做
    // 利用中序遍历，从最小的节点开始计算
    public static List<Integer> closestKValues(TreeNode root, double target, int k) {

        LinkedList<Integer> res = new LinkedList<>();
        dfs(res, root, target, k);
        return res;
    }

    public static void dfs(LinkedList<Integer> res, TreeNode root, double target, int k) {
        if (root == null) return;
        dfs(res, root.left, target, k);

        // inorder operation
        if (res.size() == k) {
            if (Math.abs(root.val - target) < Math.abs(res.peekFirst() - target)) {
                res.removeFirst();
            } else return; 
            // 这里的return是结束的意思了。如果有k个了并且当前数不会更接近的话，
            //所以就暂停，因为这里是中序遍历，后面只会更大
        }
        res.add(root.val);
        dfs(res, root.right, target, k);
    }

    public static void main(String[] args) {
        String treeStr = "[5,3,7,1,4,null,9]";
        ConverterForTreeAndString treeMain = new ConverterForTreeAndString();
        TreeNode root = treeMain.stringToTreeNode(treeStr);
        List<Integer> res = closestKValues(root, 3.1, 3);
        for (int num : res) {
            System.out.println(num);
        }
    }
}
