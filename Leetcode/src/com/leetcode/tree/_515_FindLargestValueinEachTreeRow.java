/*
 * @Date: 2020-01-09 10:56:18
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-22 14:17:43
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _515_FindLargestValueinEachTreeRow {
    // time:O(n) space:O(n)
    public List<Integer> largestValues(TreeNode root) {
        // bfs
        if (root == null) return new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max  = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            res.add(max);
        }
        return res;
    }

    // DFS的话，思路和之前每层加入层数一样例利用res.size() == level
    public List<Integer> largestValues2(TreeNode root) {
        // 明显prefer BFS
       if (root == null) return new ArrayList<>();
       List<Integer> res = new ArrayList<>();
       dfs(root, res, 0);
       return res;
   }
   
   public void dfs(TreeNode root, List<Integer> res, int depth) {
       if (root == null) return;
       if (res.size() == depth) {
           res.add(root.val);
       } else {
           res.set(depth, Math.max(root.val, res.get(depth)));
       }
       dfs(root.left, res, depth + 1);
       dfs(root.right, res, depth + 1);
   }
}
