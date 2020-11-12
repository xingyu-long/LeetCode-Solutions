package com.leetcode.greedy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Date: 04/22/2020, 05/06/2020
 * @Description: Greedy,
 **/
public class _846_HandofStraights {

    // 相当于一个个去找。从小的开始
    // time:O(NlogN + N)
    public boolean isNStraightHand(int[] hand, int W) {
        if (hand == null || hand.length == 0) {
            return false;
        }
        int n = hand.length;
        if (n % W != 0) {
            return false;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // nlogn
        Arrays.sort(hand);
        for (int num : hand) {
            int k = W;
            if (map.getOrDefault(num, 0) == 0) {
                continue;
            }
            for (int i = 0; i < k; i++) {
                if (map.getOrDefault(num + i, 0) > 0) {
                    map.put(num + i, map.get(num + i) - 1);
                } else {
                    return false;
                }
            }
        }
        for (int key : map.keySet()) {
            if (map.get(key) > 0) {
                return false;
            }
        }
        return true;
    }

    // TreeMap: MlogM, M is the number of different cards
    public boolean isNStraightHand2(int[] hand, int W) {
        if (hand == null || hand.length == 0) {
            return false;
        }
        Map<Integer, Integer> map = new TreeMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int i : map.keySet()) {
            if (map.get(i) > 0) {
                for (int j = W - 1; j >= 0; j--) {
                    if (map.getOrDefault(i + j, 0) < map.get(i)) {
                        return false;
                    }
                    map.put(i + j, map.get(i + j) - map.get(i));
                }
            }
        }
        return true;
    }
}
