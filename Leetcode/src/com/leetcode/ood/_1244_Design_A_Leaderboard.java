package com.leetcode.ood;

import java.util.*;

public class _1244_Design_A_Leaderboard {
    // 一开始想到用PQ但是那个没有treemap直接遍历有序（不用重复插入）高效，这里的treemap就是值对应相应的个数。
    Map<Integer, Integer> map;
    TreeMap<Integer, Integer> treemap;

    public _1244_Design_A_Leaderboard() {
        map = new HashMap<>();
        treemap = new TreeMap<>(Collections.reverseOrder());
    }

    public void addScore(int playerId, int score) {
        if (!map.containsKey(playerId)) {
            map.put(playerId, score);
            treemap.put(score, treemap.getOrDefault(score, 0) + 1);
        } else {
            int prevScore = map.get(playerId);
            treemap.put(prevScore, treemap.get(prevScore) - 1);
            if (treemap.get(prevScore) == 0) {
                treemap.remove(prevScore);
            }
            int newScore = prevScore + score;
            map.put(playerId, newScore);
            treemap.put(newScore, treemap.getOrDefault(newScore, 0) + 1);
        }
    }

    public int top(int K) {
        int sum = 0;
        for (int val : treemap.keySet()) {
            int times = treemap.get(val);
            for (int i = 0; i < times; i++) {
                sum += val;
                K--;
                if (K == 0) {
                    break;
                }
            }
            if (K == 0) {
                break;
            }
        }
        return sum;
    }

    public void reset(int playerId) {
        int score = map.get(playerId);
        treemap.put(score, treemap.get(score) - 1);
        if (treemap.get(score) == 0) {
            treemap.remove(score);
        }
        map.remove(playerId);
    }
}
