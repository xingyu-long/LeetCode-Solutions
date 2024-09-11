/*
 * @Date: 2020-03-25 13:59:36
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-25 15:39:15
 */
package com.leetcode.tree.bst;

import com.leetcode.common.ConverterForTreeAndString;
import com.leetcode.common.TreeNode;

public class _501_FindModeinBinarySearchTree {
    // soluiton1: 中序遍历并且使用map记录，可以work，但是follow up指的是不需要额外的space
    // solution2: two pass
    private int currVal;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;
    private int[] res;
    
    public int[] findMode(TreeNode root) {
        dfs(root);
        res = new int[modeCount];
        modeCount = 0;
        // 保留了maxCount的结果，以及modeCount的结果
        currCount = 0;
        dfs(root);
        return res;
    }
    
    public void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.left);
        handle(root.val);
        dfs(root.right);
    }
    
    public void handle(int val) {
        if (currVal != val) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        } else if (currCount == maxCount) {
            if (res != null) {
                res[modeCount] = currVal;
            }
            modeCount++;
        }
    }
    
    public static void main(String[] args) {
        _501_FindModeinBinarySearchTree findMode = new _501_FindModeinBinarySearchTree();
        String input = "[1,null,2,2,3,null,null,null,3,null,3]";
        ConverterForTreeAndString converter = new ConverterForTreeAndString();
        TreeNode root = converter.stringToTreeNode(input);
        findMode.findMode(root);
    }
}