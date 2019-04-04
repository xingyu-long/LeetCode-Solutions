package com.leetcode;

public class _69_SqrtX {

    /**
     * 69. Sqrt(x)
     * When: 2019/04/04
     *
     * solution:
     * 使用二分法 time: O(n) space: O(1)
     * 这里还有个牛顿法 No.367
     * @param x
     * @return
     */
    public static int mySqrt(int x) {
        /**
         *
         如果遇到快越界的值，这个就不能work
        int start = x / 2;
        if (x == 1) return 1;
        for (int i = start; i >=0; i--) {
            if (x >= i * i) {
                return i;
            }
        }
        return 0;
         */
        //二分法
        if (x <= 0) return 0;
        int low = 1, high = x;
        while(low <= high) {
            long mid = (high - low) / 2 + low;
            if (mid * mid == x) {
                return (int)mid;
            } else if(mid * mid < x) {
                low = (int) mid + 1;
            } else {
                high = (int) mid - 1;
            }
        }
        //这种情况就是指不能完整开方的  这里的时候low > high 因为需要走出上面的循环，所以high * high < x这就可以return high
        if (high * high < x) {
            return high;
        } else {
            return low;
        }
    }

    public static void main(String[] args) {
        System.out.println(mySqrt(26));
    }
}
