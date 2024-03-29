package com.leetcode.geometry;

import java.util.ArrayList;
import java.util.List;

public class _2013_DetectSquares {
    class DetectSquares {

        List<int[]> list;
        int[][] count;
        
        /*
         * Solution:
         * 对于四个点，已知的点是（x1, y1），先去找对角的点（x3，y3），然后这样就知道另外两个点坐标
         * 分别是（x3，y1） 和 （x1，y3）然后看这个个数，相乘相加即可
         */
        public DetectSquares() {
            list = new ArrayList<>();
            count = new int[1001][1001];
        }

        public void add(int[] point) {
            count[point[0]][point[1]] += 1;
            list.add(point);
        }

        public int count(int[] point) {
            int res = 0;
            int x1 = point[0], y1 = point[1];
            for (int[] p : list) {
                int x3 = p[0], y3 = p[1];
                if (Math.abs(x1 - x3) == 0 || Math.abs(x1 - x3) != Math.abs(y1 - y3)) {
                    continue;
                }
                res += count[x3][y1] * count[x1][y3];
            }
            return res;
        }
    }
}
