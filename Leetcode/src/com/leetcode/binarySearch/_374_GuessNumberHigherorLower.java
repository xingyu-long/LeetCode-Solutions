package com.leetcode.binarySearch;

public class _374_GuessNumberHigherorLower {

    /**
     * 374. Guess Number Higher or Lower
     * When: 2019/7/20
     *
     * solution:
     * 使用template1，返回结果是 pick相对于现在刚好选中的数字的比较。 lower 表示 pick比我选中的低
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


    public int guessNumber2(int n) {
        if (n < 1) return -1;
        int left = 1;
        int right = n;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (guess(mid) == -1) { // 这个表示目标更小所以我们只需要缩小右边jie
             right = mid;
            } else {
                left = mid;
            }
        }
        if (guess(left) == 0) return left;
        else return right;
    }

    // 防止编译器报错
    public int guess(int num) {
        return 0;
    }
}
