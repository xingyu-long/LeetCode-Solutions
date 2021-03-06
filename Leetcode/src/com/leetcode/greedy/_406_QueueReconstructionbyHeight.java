package com.leetcode.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date: 06/06/2020
 * @Description: Greedy, Sort
 **/
public class _406_QueueReconstructionbyHeight {

    // time:O(nlogn) space:O()
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 ||
            people[0] == null || people[0].length == 0) {
            return new int[][]{};
        }
        // 一定会存在 [x, 0] 的情况，因为这样才符合题意的要求。
        Arrays.sort(people, (a, b) -> (a[0] != b[0] ? b[0] - a[0] : a[1] - b[1]));
        List<int[]> list = new ArrayList<>();
        for (int[] curr : people) {
            // 插入到该位置，并且表明前面有几个比它大的情况
            list.add(curr[1], curr);
        }
        return list.toArray(new int[list.size()][]);
    }
}
