package com.leetcode.greedy;

import java.util.Arrays;

/**
 * @Date: 10/10/2020
 * @Description: Greedy
 **/
public class _455_AssignCookies {
    //time:O(nlogn) space:O(1)
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int child = 0; // 表示满足了几个孩子
        int cookie = 0; //表示尝试了几个糖果（有的没成功）
        while (child < g.length && cookie < s.length) {
            if (g[child] <= s[cookie]) { //满足需求，则直接满足该孩子即可
                child++;
            }
            cookie++;
        }
        return child;
    }
}
