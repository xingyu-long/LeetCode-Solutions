package com.intern;

import java.util.Arrays;

public class PsyTesting {

    public static int[] jobOffers(int[] scores, int[] lowLimits, int[] upperLimits) {
        if (scores == null || scores.length == 0) return new int[]{};
        int[] res = new int[lowLimits.length];
        Arrays.fill(res, 0);
        for (int i = 0; i < lowLimits.length; i++) {
            int upper = findUpperBound(scores, upperLimits[i]);
            int lower = findLowerBound(scores, lowLimits[i]);
            if (upper == -1 && lower == -1) {
                continue;
            } else {
                if (scores[lower] == lowLimits[i] || scores[upper] == upperLimits[i]) res[i]++;
                res[i] += upper - lower;
            }
        }
        return res;
    }

    public static int findLowerBound(int[] scores, int low) {
        if (scores[0] > low) return 0;
        int left = 0;
        int right = scores.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] >= low) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (scores[left] >= low) return left;
        else if (scores[right] >= low) return right;
        else return -1;
    }

    public static int findUpperBound(int[] scores, int upper) {
        if (scores[scores.length - 1] < upper) return scores.length - 1;
        int left = 0;
        int right = scores.length - 1;
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (scores[mid] > upper) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (scores[left] >= upper) return left;
        else if (scores[right] >= upper) return right;
        else return -1;
    }

    public static void main(String[] args) {
        int[] scores = new int[]{1,3,5,6,8};
        int[] lowLimits = new int[]{3};
        int[] upperLimits = new int[]{6};
//        System.out.println(findLowerBound(scores, 2));
//        System.out.println(findUpperBound(scores, 6));

        int[] res = jobOffers(scores, lowLimits, upperLimits);
        for (int num : res) {
            System.out.println(num);
        }
    }
}
