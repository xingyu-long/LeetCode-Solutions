package com.leetcode.binarySearch;

public class _374_GuessNumberHigherorLower {

    /**
     * 374. Guess Number Higher or Lower
     * When: 2019/7/20
     *
     * solution:
     * 使用template1，然后注意的是返回结果 my number代表的是pick那个数 而不是guess的数大小
     * @param n
     * @return
     */
    public int guessNumber(int n) {
        int lo = 1;
        int hi = n;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (guess(mid) == 1) {
                lo = mid + 1;
            } else if (guess(mid) == -1) {
                hi = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    // 防止编译器报错
    public int guess(int num) {
        return 0;
    }
}
