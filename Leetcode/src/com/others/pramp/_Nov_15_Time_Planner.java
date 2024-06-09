package com.pramp;

public class _Nov_15_Time_Planner {

    static int[] meetingPlanner(int[][] slotsA, int[][] slotsB, int dur) {
        // brute force
        int lenA = slotsA.length;
        int lenB = slotsB.length;
        int i = 0, j = 0;
        while (i < lenA && j < lenB) {
            // 不管有没有交叉都这么算。没有交叉的话 diff肯定会 <= 0
            int startTime = Math.max(slotsA[i][0], slotsB[j][0]);
            int endTime = Math.min(slotsA[i][1], slotsB[j][1]);
            int diff = endTime - startTime;
            if (diff >= dur) return new int[]{startTime, startTime + dur};
            if (slotsA[i][1] > slotsB[j][1]) j++;
            else i++;
        }
        return new int[]{};
    }
}
