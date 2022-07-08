package com.leetcode.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Date: 08/23/2020
 * @Description: Graph, DFS
 **/
public class _399_EvaluateDivision {
    // time:O(V + E) space:O(n)
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> map = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            List<String> item = equations.get(i);
            String first = item.get(0), second = item.get(1);
            map.putIfAbsent(first, new HashMap<>());
            map.putIfAbsent(second, new HashMap<>());
            map.get(first).put(second, values[i]);
            map.get(second).put(first, 1.0 / values[i]);
        }
        double[] res = new double[queries.size()];
        int index = 0;
        for (List<String> query : queries) {
            String start = query.get(0), end = query.get(1);
            if (!map.containsKey(start) || !map.containsKey(end))
                res[index] = -1.0;
            else
                res[index] = find(map, start, end, new HashSet<>());
            index++;
        }
        return res;
    }

    public double find(Map<String, Map<String, Double>> map, String start, String end, Set<String> visited) {
        if (!map.containsKey(start)) {
            return -1.0;
        }
        if (start.equals(end)) {
            return 1.0;
        }
        visited.add(start);
        Map<String, Double> nexts = map.get(start);
        if (nexts != null && nexts.size() > 0) {
            for (String next : nexts.keySet()) {
                if (visited.contains(next))
                    continue;
                double currVal = nexts.get(next);
                double nextVal = find(map, next, end, visited);

                if (nextVal > 0) {
                    return currVal * nextVal;
                }
            }
        }
        return -1.0;
    }
}
