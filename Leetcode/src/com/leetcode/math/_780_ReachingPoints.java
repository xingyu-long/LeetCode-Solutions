package com.leetcode.math;

/**
 * @Date: 07/20/2020
 * @Description: Math
 **/
public class _780_ReachingPoints {

    // 利用 % 来加快，并且因为题目是
    // (x, y) and transforming it to either (x, x+y) or (x+y, y).
    // 所以用大的那个数求小的数模就可以了
    // time: O(LogN)
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        while (sx < tx && sy < ty) {
            if (tx < ty) {
                ty %= tx;
            } else {
                tx %= ty;
            }
        }
        return sx == tx && sy <= ty && (ty - sy) % sx == 0 ||
            sy == ty && sx <= tx && (tx - sx) % sy == 0;
    }
}
