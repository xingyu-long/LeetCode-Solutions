package com.leetcode.math;

public class _69_SqrtX {

    /**
     *  69. Sqrt(x)
     *  When: 2019/04/04
     *  When:2019/7/20
     *
     * solution:
     * 使用二分法
     * 这里还有个牛顿法 No.367
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        //二分法
        // time: O(logN) space: O(1)
        if (x <= 0) return 0;
        int lo = 1, hi = x;
        while(lo <= hi) {
            long mid = (hi - lo) / 2 + lo;
            if (mid * mid == x) {
                return (int)mid;
            } else if(mid * mid < x) {
                lo = (int) mid + 1;
            } else {
                hi = (int) mid - 1;
            }
        }
        //这种情况就是指不能完整开方的
        // 就是因为最后的时候 lo = hi，然后mid * mid > n 导致right = mid -1 因为left * left 也大了所以最后就是返回小1的hi
        // 这里的时候low > high
        /**
         * eg: 1, 2, 3, 4, 5, 6, 7, 8
          (1) lo = 1, hi = 8 mid = 4;
              mid * mid >  8 所以在左边
               hi = 4 - 1 = 3;
          (2) lo = 1, hi = 3 mid = 2
               mid * mid < 8 所以选右边
               lo = mid + 1 = 3
          (3) lo = 3, hi = 3 mid = 3
               mid * mid > 8 所以选左边
               hi = mid - 1;

         */
        return hi;
    }
}
