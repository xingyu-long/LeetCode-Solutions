/*
 * @Date: 12/29/2020 09:38:32
 * @LastEditTime: 12/29/2020 10:10:10
 * @Description: You need to fill out
 */
package com.leetcode.tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leetcode.common.TreeNode;

public class _1457_PseudoPalindromicPathsInABinaryTree {
    // brute force. 得到path然后验证。
    public int pseudoPalindromicPaths(TreeNode root) {
        if (root == null) {
            return 0;
        }
        List<List<Integer>> path = new ArrayList<>();
        findPath(path, new ArrayList<>(), root);
        int res = 0;
        for (List<Integer> p : path) {
            if (isValid(p)) {
                res++;
            }
        }
        return res;
    }

    private void findPath(List<List<Integer>> path, List<Integer> list, TreeNode root) {
        if (root == null) {
            return;
        }
        list.add(root.val);
        if (root.left == null && root.right == null) {
            path.add(new ArrayList<>(list));
        }
        findPath(path, list, root.left);
        findPath(path, list, root.right);
        list.remove(list.size() - 1);
    }

    private boolean isValid(List<Integer> list) {
        Set<Integer> set = new HashSet<>();
        for (int num : list) {
            if (set.contains(num)) {
                set.remove(num);
            } else {
                set.add(num);
            }
        }
        return set.size() <= 1;
    }

    // Time:O(N )
    public int pseudoPalindromicPaths2 (TreeNode root) {
        // 利用XOR
        return dfs(root, 0);
    }
    
    private int dfs(TreeNode root, int state) {
        if (root == null) {
            return 0;
        }
        state ^= (1 << root.val);
        int res = 0;
        if (root.left == null && root.right == null) {
            return countOnes(state) <= 1 ? 1 : 0;
        }
        res += dfs(root.left, state);
        res += dfs(root.right, state);
        return res;
    }
    
    private int countOnes(int state) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            if ((state & 1) == 1) res++;
            state >>= 1;
        }
        return res;
    }
}
