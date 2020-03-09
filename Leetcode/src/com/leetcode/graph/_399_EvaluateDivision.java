package com.leetcode.graph;

import java.util.*;

public class _399_EvaluateDivision {

    /**
     * 399. Evaluate Division
     * When:02/26/2020
     * @param equations
     * @param values
     * @param queries
     * @return
     */

    // time:O(V + E) space:O(n)
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, HashMap<String, Double>> map = new HashMap<>();
        // build the graph
        for (int i = 0; i < equations.size(); i++) {
            String x = equations.get(i).get(0);
            String y = equations.get(i).get(1);
            double val = values[i];
            if (!map.containsKey(x)) map.put(x, new HashMap<String, Double>());
            map.get(x).put(y, val);
            if (!map.containsKey(y)) map.put(y, new HashMap<String, Double>());
            map.get(y).put(x, 1 / val);
        }
        double[] res = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String x = queries.get(i).get(0);
            String y = queries.get(i).get(1);
            if (! map.containsKey(x) || ! map.containsKey(y)) res[i] = -1.0;
            else res[i] = calculateHelper(x, y, map, new HashSet<String>());
        }
        return res;
    }


    public double calculateHelper(String start, String end, HashMap<String, HashMap<String, Double>> map, HashSet<String> visited) {
        if (start.equals(end)) return 1.0;
        if (!map.containsKey(start)) return -1.0;
        visited.add(start);
        for (String n : map.get(start).keySet()) {
            if (visited.contains(n)) continue;
            double res = calculateHelper(n, end, map, visited);
            if (res > 0) return res * map.get(start).get(n); // 这里也很重要
        }
        return -1.0;
    }

    public static void main(String[] args) {
        List<List<String>> lists = new ArrayList<>();
        lists.add(Arrays.asList("a", "b"));
        lists.add(Arrays.asList("b", "c"));
        double[] values = new double[]{2.0, 3.0};
        List<List<String>> queries = new ArrayList<>();
        queries.add(Arrays.asList("a", "c"));
        queries.add(Arrays.asList("b", "a"));
        queries.add(Arrays.asList("a", "e"));
        queries.add(Arrays.asList("a", "a"));
        queries.add(Arrays.asList("x", "x"));
        _399_EvaluateDivision division = new _399_EvaluateDivision();
        division.calcEquation(lists, values, queries);
    }
}
