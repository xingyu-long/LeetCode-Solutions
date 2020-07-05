package com.leetcode.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Date: 07/03/2020
 * @Description: Simulation, MOD
 **/
public class _957_PrisonCellsAfterNDays {
    // time:(mod=14) space:O(1)
    public int[] prisonAfterNDays(int[] cells, int N) {
        if (cells == null || cells.length == 0) {
            return new int[]{};
        }
        Set<String> visited = new HashSet<>();
        boolean hasCycle = false;
        int count = 0;
        for (int i = 0; i < N; i++) {
            // 先存下下一天的情况，这里用的很妙
            int[] next = nextDay(cells);
            String key = Arrays.toString(next);
            if (!visited.contains(key)) {
                visited.add(key);
                count++;
            } else {
                hasCycle = true;
                break;
            }
            cells = next;
        }
        if (hasCycle) {
            N %= count;
            for (int i = 0; i < N; i++) {
                cells = nextDay(cells);
            }
        }
        return cells;
    }

    private int[] nextDay(int[] cells) {
        int[] temp = new int[cells.length];
        for (int i = 1; i < cells.length - 1; i++) {
            temp[i] = (cells[i - 1] == cells[i + 1] ? 1: 0);
        }
        return temp;
    }
}
