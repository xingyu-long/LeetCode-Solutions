package com.leetcode.matrix;

public class _302_SmallestRectangleEnclosingBlackPixels {
    // brute force
    // 走完每一个 '1'的的地方然后更新那个范围，计算上下左右的的边界就可以了。
    // time:O(n^2) space:O(1)
    public static int minArea(char[][] image, int x, int y) {
        int left = y, right = y;
        int top = x, bottom = x;
        int m = image.length;
        int n = image[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (image[i][j] == '1') {
                    left = Math.min(left, j);
                    right = Math.max(right, j);
                    top = Math.min(top, i);
                    bottom = Math.max(bottom, i);
                }
            }
        }
        return (right - left + 1) * (bottom - top + 1);
    }

    // 利用binary search
    // time:O(mlogn + nlogm)
    // space:O(1)
    public static int minArea2(char[][] image, int x, int y) {
        int rows = image.length;
        int cols = image[0].length;
        // 注意边界，干脆算入这样binary search的里面不用考虑边界。
        int left = findLeftOrTop(image, 0, y, true);
        int right = findRightOrBottom(image, y, cols - 1, true);
        int top = findLeftOrTop(image, 0, x, false);
        int bottom = findRightOrBottom(image, x, rows - 1, false);
        return (right - left + 1) * (bottom - top + 1);
    }

    public static int findLeftOrTop(char[][] image, int left, int right, boolean isHor) {
        while (left + 1 < right) {
            int mid = (right - left) / 2 + left;
            if (hashBlack(image, mid, isHor)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        if (hashBlack(image, left, isHor)) {
            return left;
        }
        return right;
    }

    public static int findRightOrBottom(char[][] image, int left, int right, boolean isHor) {
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
            if (hashBlack(image, mid, isHor)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        if (hashBlack(image, right, isHor)) return right;
        else return left;
    }

    public static boolean hashBlack(char[][] image, int x, boolean isVertical) {
        if (isVertical) {
            for (int i = 0; i < image.length; i++) {
                if (image[i][x] == '1') return true;
            }
        } else {
            for (int j = 0; j < image[0].length; j++) {
                if (image[x][j] == '1') return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] image = {{'0', '0', '1', '0'},
                {'0', '1', '1', '0'},
                {'0', '1', '1', '1'}};
        System.out.println(minArea2(image, 0, 2));
    }
}
