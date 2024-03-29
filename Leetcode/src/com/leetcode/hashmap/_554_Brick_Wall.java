/*
 * @Date: 09/06/2020 23:21:08
 * @LastEditTime: 08/08/2022 16:30:07
 * @Description: prefix, hashmap
 */
package com.leetcode.hashmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _554_Brick_Wall {
    // 计算累计和出现的频率，如果频率越大，则可以选择当前的线，即n - 最大的频率的个数
    // 之前做题也是一样的思路，主要看每列的重叠的元素（累计和的情况）
    // 这种处理方式更加elegant 并且不需要修改原来的值。
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null || wall.size() == 0) {
            return 0;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int n = wall.size();
        int maxCommonCount = 0;
        for (List<Integer> rows : wall) {
            int sum = 0;
            // 不用检查和为最后的时候，因为那种情况maxCommonCount就为wall.size();
            for (int j = 0; j < rows.size() - 1; j++) {
                sum += rows.get(j);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
                maxCommonCount = Math.max(maxCommonCount, map.get(sum));
            }
        }
        return n - maxCommonCount;
    }
}
