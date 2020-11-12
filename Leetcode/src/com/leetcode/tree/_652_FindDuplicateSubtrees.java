package com.leetcode.tree;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _652_FindDuplicateSubtrees {
    List<TreeNode> res;
    Map<String, Integer> map;

    // time: O(N)
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        map = new HashMap<>();
        build(root);
        return res;
    }

    public String build(TreeNode root) {
        if (root == null) {
            return "#";
        }

        String left = build(root.left);
        String right = build(root.right);

        String encode = root.val + "," + left + "," + right;
        map.put(encode, map.getOrDefault(encode, 0) + 1);
        if (map.get(encode) == 2) {
            res.add(root);
        }
        return encode;
    }
}