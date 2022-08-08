package com.leetcode.bfs_and_dfs;

public class _1274_NumberofShipsinaRectangle {
    static class Sea {
        public boolean hasShips(int[] topRight, int[] bottomLeft) {
            return true;
        }
    }
    // Divide and conquer
    public int countShips(Sea sea, int[] topRight, int[] bottomLeft) {
        if (!sea.hasShips(topRight, bottomLeft)) return 0;
        int x2 = topRight[0], y2 = topRight[1];
        int x1 = bottomLeft[0], y1 = bottomLeft[1];
        if (x1 == x2 && y1 == y2) return 1; // 重合了，base case

        int midX = (x1 + x2) / 2;
        int midY = (y1 + y2) / 2;
        //分成四个区域去寻找
        return countShips(sea, new int[]{midX, midY}, bottomLeft) +
                countShips(sea, topRight, new int[]{midX + 1, midY + 1}) +
                countShips(sea, new int[]{midX, y2}, new int[]{x1, midY + 1}) +
                countShips(sea, new int[]{x2, midY}, new int[]{midX + 1, y1});
    }
}
