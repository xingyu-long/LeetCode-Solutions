/*
 * @Date: 2019-08-25 17:07:38
 * 
 * @LastEditors: Clark long
 * 
 * @LastEditTime: 2020-04-02 15:00:27
 */
package com.leetcode.tree;

import com.leetcode.common.TreeNode;

public class _106_ConstructBinaryTreefromInorderandPostorderTraversal {

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return dfs(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }

    public TreeNode dfs(int[] inorder, int[] postorder, int istart, int iend, int pstart,
            int pend) {
        if (istart > iend || pstart > pend)
            return null;
        TreeNode root = new TreeNode(postorder[pend]);
        int index = 0;
        for (index = istart; index <= iend; index++) {
            if (postorder[pend] == inorder[index]) {
                break;
            }
        }
        int len = index - istart;
        root.left = dfs(inorder, postorder, istart, istart + len - 1, pstart, pstart + len - 1);// 就让len和start这些产生关系！
        root.right = dfs(inorder, postorder, index + 1, iend, pstart + len, pend - 1);
        return root;
    }
}
