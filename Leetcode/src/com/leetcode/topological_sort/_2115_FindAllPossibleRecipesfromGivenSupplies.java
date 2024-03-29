/*
 * @Date: 07/18/2022 12:24:35
 * @LastEditTime: 07/18/2022 13:02:44
 * @Description: You need to fill out
 */
package com.leetcode.topological_sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class _2115_FindAllPossibleRecipesfromGivenSupplies {
    public List<String> findAllRecipes(String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, Integer> indegree = new HashMap<>();
        
        for (int i = 0; i < ingredients.size(); i++) {
            for (String s : ingredients.get(i)) {
                graph.putIfAbsent(s, new HashSet<>());
                graph.get(s).add(recipes[i]);
                // ingredient -> recipe
                indegree.put(recipes[i], indegree.getOrDefault(recipes[i], 0) + 1);
            }
        }
        
        Queue<String> queue = new LinkedList<>();
        // 这些默认入度为0
        for (String item : supplies) {
            queue.offer(item);
        }
        while (!queue.isEmpty()) {
            String curr = queue.poll();
            if (graph.get(curr) != null) {
                for (String recipe : graph.get(curr)) {
                    indegree.put(recipe, indegree.get(recipe) - 1);
                    if (indegree.get(recipe) == 0) {
                        // 因为当前这个recipe有可能被作为其他的ingredient
                        queue.offer(recipe);
                    }
                }
            }
        }
        List<String> res = new ArrayList<>();
        for (String recipe : recipes) {
            if (indegree.get(recipe) == 0) {
                res.add(recipe);
            }
        }
        return res;
    }
}
