/*
 * @Date: 06/26/2022 21:24:27
 * @LastEditTime: 06/26/2022 21:33:19
 * @Description: You need to fill out
 */
package com.leetcode.array.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _1229_MeetingScheduler {
    // time: O(nlogn) space: O(1)
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        int i = 0, j = 0;
        int len1 = slots1.length, len2 = slots2.length;
        Arrays.sort(slots1, (a, b) -> (a[0] - b[0]));
        Arrays.sort(slots2, (a, b) -> (a[0] - b[0]));
        while (i < len1 && j < len2) {
            
            if (slots2[j][0] > slots1[i][1]) {
                i++;
            } else if (slots1[i][0] > slots2[j][1]) {
                j++;
            } else if (slots2[j][1] >= slots1[i][0] && slots1[i][1] >= slots2[j][1]) {
                // intersect
                int start = Math.max(slots1[i][0], slots2[j][0]);
                int end = Math.min(slots1[i][1], slots2[j][1]);
                if (end - start >= duration) {
                    return new ArrayList<>(Arrays.asList(start, start + duration));
                } else {
                    j++;
                }
            } else if (slots2[j][0] <= slots1[i][1] && slots1[i][1] <= slots2[j][1]) {
                // intersect
                int start = Math.max(slots1[i][0], slots2[j][0]);
                int end = Math.min(slots1[i][1], slots2[j][1]);
                if (end - start >= duration) {
                    return new ArrayList<>(Arrays.asList(start, start + duration));
                } else {
                    i++;
                }
            }
        }
        return new ArrayList<>();
    }

    // 更加简洁的解法：直接去找可能的intersection area（如果不存在，相减会小于0）
    // 然后再去找end长的一边，另外一边则需要移动其指针
    public List<Integer> minAvailableDuration2(int[][] slots1, int[][] slots2, int duration) {
        int i = 0, j = 0;
        int len1 = slots1.length, len2 = slots2.length;
        Arrays.sort(slots1, (a, b) -> (a[0] - b[0]));
        Arrays.sort(slots2, (a, b) -> (a[0] - b[0]));
        while (i < len1 && j < len2) {
            // intersection area
            // 公共起始时间，较晚的一个时间
            int start = Math.max(slots1[i][0], slots2[j][0]);
            // 公共结束时间，较早的一个时间
            int end = Math.min(slots1[i][1], slots2[j][1]);
            if (end - start >= duration) {
                return new ArrayList<>(Arrays.asList(start, start + duration));
            } else if (slots1[i][1] < slots2[j][1]) {
                // 结束时间早的换到下一个interval
                i++; 
            } else {
                j++;
            }
        }
        return new ArrayList<>();
    }
}
