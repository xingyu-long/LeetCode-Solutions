/*
 * @Date: 2019-09-12 16:32:01
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-21 17:05:46
 */
package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class _113_PathSumII {
    //time: O(n) space:O(n)
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        helper(res, new ArrayList<>(),root, sum);
        return res;
    }

    public static void helper(List<List<Integer>> res, List<Integer> cur,TreeNode root, int sum) {
        if (root == null) return;
        cur.add(root.val);
        sum -= root.val; // 具有栈的特征，可以在每一层表示那一层的情况
        if (root.left == null && root.right == null && sum == 0) {
            res.add(new ArrayList<>(cur)); // 这样才可以保留单独不同的list，否则会被当成同一个处理掉
            // 这里不用return，因为最后还需要把cur的最后一个元素移除
        }
        helper(res, cur, root.left, sum);
        helper(res, cur, root.right, sum);
        cur.remove(cur.size() - 1);
    }
}
