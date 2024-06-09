package com.new_grad.amazon;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Nearest_City {
    // 构建两个对应的map即可。
    private static String[] solve(String[] cities, int[] xs, int[] ys, String[] queries) {
        String[] res = new String[cities.length];
        Map<Integer, TreeMap<Integer, String>> xMap = new HashMap<>();
        Map<Integer, TreeMap<Integer, String>> yMap = new HashMap<>();
        Map<String, int[]> nodeMap = new HashMap<>();
        for (int i = 0; i < cities.length; i++) {
            nodeMap.put(cities[i], new int[]{xs[i], ys[i]});
            xMap.putIfAbsent(xs[i], new TreeMap<>());
            yMap.putIfAbsent(ys[i], new TreeMap<>());
            xMap.get(xs[i]).put(ys[i], cities[i]);
            yMap.get(ys[i]).put(xs[i], cities[i]);
        }

        for (int i = 0; i < queries.length; i++) {
            int[] node = nodeMap.get(queries[i]);
            TreeMap<Integer, String> ym = xMap.getOrDefault(node[0], new TreeMap<>());
            TreeMap<Integer, String> xm = yMap.getOrDefault(node[1], new TreeMap<>());
            Integer lowerY = ym.lowerKey(node[1]), higherY = ym.higherKey(node[1]);
            Integer lowerX = xm.lowerKey(node[0]), higherX = xm.higherKey(node[0]);
            // check the up direction;
            int dist = Integer.MAX_VALUE;
            if (lowerY != null && Math.abs(lowerY - node[1]) <= dist) {
                dist = Math.abs(lowerY - node[1]);
                res[i] = res[i] == null ? ym.get(lowerY) : res[i].compareTo(ym.get(lowerY)) > 0 ? ym.get(lowerY) : res[i];
            }

            if (higherY != null && Math.abs(higherY - node[1]) <= dist) {
                dist = Math.abs(higherY - node[1]);
                res[i] = res[i] == null ? ym.get(higherY) : res[i].compareTo(ym.get(higherY)) > 0 ? ym.get(higherY) : res[i];
            }

            if (lowerX != null && Math.abs(lowerX - node[0]) <= dist) {
                dist = Math.abs(lowerX - node[0]);
                res[i] = res[i] == null ? xm.get(lowerX) : res[i].compareTo(xm.get(lowerX)) > 0 ? xm.get(lowerX) : res[i];
            }

            if (higherX != null && Math.abs(higherX - node[0]) <= dist) {
                dist = Math.abs(higherX - node[0]);
                res[i] = res[i] == null ? xm.get(higherX) : res[i].compareTo(xm.get(higherX)) > 0 ? xm.get(higherX) : res[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String[] cities = {"c1", "c2", "c3"};
        int[] xs = {3, 2, 1};
        int[] ys = {3, 2, 3};
        String[] queries = {"c1", "c2", "c3"};
        String[] res = solve(cities, xs, ys, queries);
        for (String temp : res) {
            System.out.println(temp);
        }
    }
}
