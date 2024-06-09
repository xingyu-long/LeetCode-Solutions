package com.intern.Quora;

public class maxRibbon {
    /**
     * Given a list representing the length of ribbon,
     * and the target number "k" parts of ribbon. we want
     * to cut ribbon into k parts with the same size,
     * at the same time we want the maximum size.
     * Ex.
     * Input: A = [1, 2, 3, 4, 9], k = 5
     * Output: 3
     * Explanation:​​​​​​​​​​​​​​​​​​​
     * if size = 1, then we have 19 parts
     * if size = 2, then we have 8 parts
     * if size = 3, then we have 5 parts
     * if size = 4, then we have 3 parts,
     * which is not enough. So return the max size = 3.
     */
    // 没懂题目意思。这个和875 猩猩吃香蕉一样
    public static int maxRibbonNum(int[] ribbons, int k) {
        if (ribbons == null || ribbons.length == 0) return 0;
        int left = 0;
        int right = 0;
        for (int ribbon : ribbons) {
            right += ribbon;
        }
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (!isValid(ribbons, mid, k)) { //如果不符合，就需要减少L
                right = mid;
            } else {
                left = mid;
            }
        }
        if (isValid(ribbons, right, k)) return right;
        else return left;
    }

    // 这里有些不同 这里只计算多少cut 所以不用求%
    public static boolean isValid(int[] ribbons, int maxL, int k) {
        int count = 0;
        for (int ribbon : ribbons) {
            count += ribbon / maxL;
            // if (ribbon % maxL != 0) count++;
        }
        return count >= k;
    }
}
