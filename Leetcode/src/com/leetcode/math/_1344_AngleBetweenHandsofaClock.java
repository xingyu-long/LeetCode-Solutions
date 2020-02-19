package com.leetcode.math;

public class _1344_AngleBetweenHandsofaClock {
    // 1344. Angle Between Hands of a Clock
    // 相当于以钟的12点位置作为起始点。
    public double angleClock(int hour, int minutes) {
        double dh = hour;
        double dm = minutes;

        double hangle = dh * 360 / 12 + dm / 60 * (360 / 12);

        hangle %= 360;

        double mangle = dm / 60 * 360;
        if (mangle > hangle) return Math.min(mangle - hangle, 360 - (mangle - hangle));
        return Math.min(hangle - mangle, 360 - (hangle - mangle));
    }
}
