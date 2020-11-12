package com.new_grad.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Count_Teams {
    // 取出来然后combination？
    static Map<String, Integer> map;
    public static int countTeams(int num, int[] skills, int minAssociates,
                          int minLevel, int maxLevel) {
        int count = 0;
        map = new HashMap<>();
        for (int i = 0; i < num; i++) {
            if (skills[i] >= minLevel && skills[i] <= maxLevel) {
                count++;
            }
        }
        int res = 0;
        for (int i = minAssociates; i <= count; i++) {
            res += comb(count, i);
        }
        // 用总的count 构成 最小到最大的情况
        return res;
    }

    // m个数组 构成长度为n的情况
    public static int comb(int m, int n) {
        String key = m + "_" + n;
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return m;
        }
        if (n > m / 2) {
            return comb(m, m - n);
        }
        if (n > 1) {
            int res = comb(m - 1, n -1) + comb(m - 1, n);
            map.put(key, res);
            return res;
        }
        return 0;
    }

    public static void main(String[] args) {
        int num=6;
        int[] skills = {12, 4, 6, 13, 5, 10};
        int min = 3;
        int minLevel = 4;
        int maxLevel = 10;
        System.out.println(countTeams(num, skills, min, minLevel, maxLevel));
    }
}
