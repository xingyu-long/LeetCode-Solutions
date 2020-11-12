package com.new_grad.amazon;

import java.util.*;

public class Disk_Space_Analysis {

    // 相当于是求segmentLength大的window里面的最小值，之间比较的最大值。
    public static int MaxInMinimal(int numComputer, List<Integer> hardDiskSpace, int segmentLength) {
        Deque<Integer> deque = new LinkedList<>();
        int res = Integer.MIN_VALUE;
        for (int i = 0; i < numComputer; i++) {

            while (!deque.isEmpty() && i - deque.peekFirst() >= segmentLength) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() &&
                    hardDiskSpace.get(i) < hardDiskSpace.get(deque.peekLast())) {
                deque.pollLast();
            }

            deque.offerLast(i);
            if (i >= segmentLength - 1) {
                System.out.println("i = " + i + " current min = " + hardDiskSpace.get(deque.peekFirst()));
                res = Math.max(res, hardDiskSpace.get(deque.peekFirst()));
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(MaxInMinimal(3, new ArrayList<>(Arrays.asList(8, 2, 4)), 2));
    }
}
