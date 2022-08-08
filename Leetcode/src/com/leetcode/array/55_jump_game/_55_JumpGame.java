package com.leetcode.array;

/**
 * @Date: 2019/03/20, 2019/7/7, 07/15/2020
 * @Description: Greedy
 **/
public class _55_JumpGame {

    /**
     * 55. Jump Game When: 2019/03/20 Review1: 2019/7/7 Difficulty: Medium
     * <p>
     *
     *
     * @param nums
     * @return
     */
    // solution: 首先初始化max=0；每一个地方能走最远的距离就是该索引i的值加上nums[i] 与 max 作比较
    // 取最大 最后比较到 index = i - 1 只要 i <  max 则表示可以到达 有点“贪心算法”的意思
    // time:(n^2) space: O(1)
    // 每次都遍历当前的maxStep里是否有更大的步伐。
    public boolean canJump2(int[] nums) {
        int n = nums.length;
        if (nums == null || n == 0) {
            return false;
        }
        int maxStep = nums[0];
        for (int i = 0; i < n; i++) {
            for (int j = i; j <= maxStep; j++) {
                maxStep = Math.max(maxStep, j + nums[j]);
                if (maxStep >= n - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    // time:O(n) space:O(1)
    public boolean canJump3(int[] nums) {
        int n = nums.length;
        int canReach = 0;
        // 相当于用这个maxStep在循环中，太棒了，不用重复计算！
        for (int i = 0; i < n && i <= canReach; i++) {
            canReach = Math.max(canReach, i + nums[i]);
            if (canReach >= n - 1) {
                return true;
            }
        }
        return false;
    }
}
