package com.new_grad.amazon;

import java.util.*;

public class Solutions {

    // 7 min 做完 还剩 63min
    long getMaxUnit(int num, ArrayList<Integer> boxes, int unitSize,
                    ArrayList<Integer> unitsPerBox, long truckSize) {
        // WRITE YOUR CODE HERE
        PriorityQueue<int[]> maxPQ = new PriorityQueue<>((a, b) -> (b[1] - a[1]));
        for (int i = 0; i < boxes.size(); i++) {
            maxPQ.offer(new int[]{boxes.get(i), unitsPerBox.get(i)});
        }
        long res = 0;
        while (truckSize > 0 && !maxPQ.isEmpty()) {
            int[] curr = maxPQ.poll();
            long canHold = Math.min(curr[0], truckSize);
            res = res + (canHold * curr[1]);
            truckSize -= canHold;
        }
        return res;
    }

    static class Point {
        public int x;
        public int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static long divide(int left, int right, Point[] points) {
        // 当前最小两点距离，初始值设置为无穷大
        long curMinDis = Long.MAX_VALUE;
        // 如果只有一个点，则不存在最近两点距离，返回无穷大
        if (left == right) {
            return curMinDis;
        }
        // 这里是判断是否为只有两个点，如果只有两个点的话那么直接求解。
        if (left + 1 == right) {
            return distance(points[left], points[right]);
        }

        // 分治法：第一步：分区，并求取左右分区最小两点距离
        // 通过右移运算除2，对区域进行合理的划分，使得左右两边保持大致相等个数点
        int middle = (left + right) >> 1;
        long leftMinDis = divide(left, middle, points);
        long rightMinDis = divide(middle, right, points);

        curMinDis = (leftMinDis <= rightMinDis) ? leftMinDis : rightMinDis;

        // 分治法：第二步：假设距离最近的两点分别在左右分区中
        // 关键代码，距离最近的两个点，一个位于左边区域，一个位于右边区域，x轴搜索范围[middle-curMinDis, middle+curMinDis]
        // 记录搜索区间内的点的索引，便于进一步计算最小距离
        List<Integer> validPointIndex = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            if (Math.abs(points[middle].x - points[i].x) <= curMinDis) {
                validPointIndex.add(i);
            }
        }
        // 基于索引，进一步计算区间内最小两点距离
        for (int i = 0; i < validPointIndex.size() - 1; i++) {
            for (int j = i + 1; j < validPointIndex.size(); j++) {
                // 如果区间内的两点y轴距离大于curMinDis，则没必要计算了，因为，它们的距离肯定大于curMinDis，
                if (Math.abs(points[validPointIndex.get(i)].y
                        - points[validPointIndex.get(j)].y) > curMinDis) {
                    continue;
                }
                long tempDis = distance(points[validPointIndex.get(i)],
                        points[validPointIndex.get(j)]);

                curMinDis = (tempDis < curMinDis) ? tempDis : curMinDis;
            }
        }

        return curMinDis;
    }

    /**
     * 计算两点间的距离
     */
    public static long distance(Point p1, Point p2) {
        return (p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x);
    }

    public static void main(String[] args) {
        // 测试用例
        Point[] points = new Point[3];

        points[0] = new Point(0, 0);
        points[1] = new Point(1, 1);
        points[2] = new Point(2, 4);

        // 预处理，基于x轴坐标排序，便于分治法实施
        Arrays.sort(points, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                return (p1.x > p2.x) ? 1 : (p1.x == p2.x) ? 0 : -1;
            }

        });
        // 测试
        System.out.println(divide(0, points.length - 1, points));
    }
}
