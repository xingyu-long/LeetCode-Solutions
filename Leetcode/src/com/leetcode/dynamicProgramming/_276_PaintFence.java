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
    public int numWays(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;
        int same = 0, diff = k, res = k; // res = same + diff
        for (int i = 2; i <= n; i++) {
            same = diff;
            diff = res * (k - 1);
            res = same + diff;
        }
        return res;
    }
}