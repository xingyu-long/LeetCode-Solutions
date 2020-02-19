package com.leetcode.tree;

import com.leetcode.common.ConverterForTreeAndString;
import com.leetcode.common.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class _508_MostFrequentSubtreeSum {
    int maxCount = 0;
    HashMap<Integer, Integer> map = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null) return new int[]{};
        List<Integer> list = new LinkedList<>();
        helper(root);
        System.out.println("maxCount = " + maxCount);
        for (int key : map.keySet()) {
            if (map.get(key) == maxCount) {
                list.add(key);
            }
        }
        int[] res = new int[list.size()];
        int i = 0;
        for (int num : list) {
            res[i++] = num;
        }
        return res;
    }

    public int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        // post order
        int sum = root.val + left + right;
        System.out.println("sum = " + sum);
        if (map.containsKey(sum)) {
            map.put(sum, map.get(sum) + 1);
            maxCount = Math.max(maxCount, map.get(sum));
        } else {
            map.put(sum, 1);
            maxCount = Math.max(maxCount, 1);
        }
        return sum;
    }

    public static void main(String[] args) {
        ConverterForTreeAndString converter = new ConverterForTreeAndString();
        String str = "[5,2,-5]";
        TreeNode root = converter.stringToTreeNode(str);
        _508_MostFrequentSubtreeSum frequentSubtreeSum = new _508_MostFrequentSubtreeSum();
        int[] res = frequentSubtreeSum.findFrequentTreeSum(root);
        for (int num : res) {
            System.out.println(num);
        }
    }
}
