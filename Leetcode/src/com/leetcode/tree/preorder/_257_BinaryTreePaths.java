package com.leetcode.tree.preorder;

import com.leetcode.common.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _257_BinaryTreePaths {

    /**
     * 257. Binary Tree Paths
     * When: 2019/04/16
     * Review1:2019/7/26
     * review2: 2019/9/12
     * solution:
     * 利用先序遍历，只是需要注意什么时候添加其字符串。
     * 相当于stack 所以string的前面例如前面几层就会保留下来 例如左边进去的时候是 1-> 那就在计算右边的时候继续保留
     *
     * @param root
     * @return
     */
    // dfs
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        helper(res, "", root);
        return res;
    }

    public static void helper(List<String> res, String s, TreeNode root) {
        if (root == null) return;
        s += Integer.toString(root.val);
        // 只有这样会加入结果中
        // how to add -> and also keep the previous part?
        if (root.left == null && root.right == null) {
            res.add(s);
            // 这里的string 初始化问题呢？ A: 进当前stack之前的s 是跟这里的不一样
            return;
        }
        s += "->";
        helper(res, s, root.left);
        helper(res, s, root.right);
    }

    //BFS 利用queue来存储string

    /**
     * Test case:
     * 1
     * / \
     * 2  3
     * /   \
     * 4    5
     * <p>
     * root = 1
     * queue: node(1)
     * stringQ: ""
     * (1) curNode = 1, curStr = "";
     * 左右子树均存在
     * queue: node(2), node(3)
     * stringQ: "1->", "1->"
     * (2) curNode = 2, curStr = "1->";
     * 只有左子树
     * queue: node(3), node(4)
     * stringQ: "1->", "1->2->";
     * (3) curNode = 3, curStr ="1->"
     * 只有右子树
     * queue: node(4), node(5)
     * stringQ: "1->2->", "1->3->";
     * (4) curNode = 4, curStr = "1->2->";
     * 左右均无 res = ["1->2->4", ];
     * (5) curNode = 5, curStr = "1->3->"
     * 左右均无 res = ["1->2->4", "1->3->5"];
     */
    public static List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<String> stringQ = new LinkedList<>();

        queue.offer(root);
        stringQ.offer("");

        while (!queue.isEmpty()) {
            TreeNode curNode = queue.poll();
            String curStr = stringQ.poll();
            // 如果符合条件则加入res
            if (curNode.left == null && curNode.right == null) {
                res.add(curStr + curNode.val);
            }
            if (curNode.left != null) {
                queue.offer(curNode.left);
                stringQ.offer(curStr + curNode.val + "->");
            }
            if (curNode.right != null) {
                queue.offer(curNode.right);
                stringQ.offer(curStr + curNode.val + "->");
            }
        }
        return res;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(10);
        root.right = new TreeNode(2);
        TreeNode right = root.right;
        right.left = new TreeNode(3);
        right.right = new TreeNode(4);
        System.out.println(binaryTreePaths(root).toString());
    }
}
