package com.leetcode.tree;

public class _331_VerifyPreorderSerializationofaBinaryTree {

    /**
     * 331. Verify Preorder Serialization of a Binary Tree
     * When:2019/9/25
     * Difficulty: Medium
     *
     * @param preorder
     * @return
     */
    // 如果是非# 则就可以用两个indegree存在，如果是#就没有indegree，但是两者都是会先消耗一个位置
    // 最后的结果为0则代表是一个有效的tree
    // https://www.youtube.com/watch?v=_mbnPPHJmTQ
    // time:O(n) space:O(n)
    public boolean isValidSerialization(String preorder) {
        int diff = 1;
        String[] nodes = preorder.split(",");
        for (String node : nodes) {
            diff--;
            if (diff < 0) return false;
            if (!node.equals("#")) {
                diff += 2;
            }
        }
        return diff == 0;
    }
}