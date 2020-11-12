package com.leetcode.dynamicProgramming;

public class _276_PaintFence {
    //当前这一个跟前面相同的时候，则相同就是原来的diff。当前与前面不相同的时候 则是以前的结果res * (k - 1)
    // 数学推导就可以了

    /**
     *  276. Paint Fence
     *  When:2019/8/1
     *  Difficulty: Easy

     There is a fence with n posts, each post can be painted with one of the k colors.

     You have to paint all the posts such that no more than two adjacent fence posts have the same color.

     Return the total number of ways you can paint the fence.

     solution:
     https://www.youtube.com/watch?v=deh7UpSRaEY
     https://www.cnblogs.com/grandyang/p/5231220.html
     * @param n
     * @param k
     * @return
     */
    // 一步一步的推导，发现当前的要和前面相同的话,需要的是前一个步骤不同的情况。
    // 发现当前的要和前面不同的话，那就是之前的总和 * (k-1)
    // 记住只考虑当前和之前一个的颜色，所以才可以用k-1。
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        int same = k;
        int diff = k * (k - 1);
        int res = same + diff;
        for (int i = 2; i < n; i++) {
            int nextSame = diff;
            int nextDiff = res * (k - 1); // 之前的所有结果 * (k-1)表示与之前的倒数第一位不同。
            res = (nextSame + nextDiff);
            diff = nextDiff;
        }
        return res;
    }
}