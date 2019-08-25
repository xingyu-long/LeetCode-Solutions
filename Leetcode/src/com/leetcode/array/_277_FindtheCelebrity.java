package com.leetcode.array;

public class _277_FindtheCelebrity {

    /**
     *  277. Find the Celebrity
     *  When:2019/7/25
     *  review1:2019/8/24
     *  Difficulty: Medium
     *
     * @param n
     * @return
     */
    // 先假定是0这个为celebrity 然后只要有人认识就更新res（因为只有这样才可能称为celebrity）
    public int findCelebrity(int n) {
        if (n < 2) return -1;
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (knows(res, i)) {
                res = i;
            }
        }
        for (int i = 0; i < n; i++) {
            // 表示如果res认识其中一个就失败或者其中有人不认识res也失败
            if (res != i && ((knows(res, i) || !knows(i, res)))) {
                return -1;
            }
        }
        return res;
    }

    // 防止报错写上的
    public boolean knows(int a, int b) {
        return true;
    }

}
