package com.leetcode.graph.UnionFind;

import java.util.*;

public class _1202_SmallestStringWithSwaps {


    public void dfs(String s, int index, HashMap<Integer, List<Integer>> map, boolean[] visited, List<Integer> indexes, List<Character> chs) {
        if (visited[index]) return;
        visited[index] = true;
        indexes.add(index);
        chs.add(s.charAt(index));
        // 对于那些没有在map中的依然需要加入到indexes以及chs中
        if (!map.containsKey(index)) return;
        List<Integer> adj = map.get(index);
        for (int i = 0; i < adj.size(); i++) {
            dfs(s, adj.get(i), map, visited, indexes, chs);
        }
    }


    // time:O(nlogn) space:O(n)
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        if (pairs.size() == 0 || pairs == null) return s;
        // 构建其map的关系，无向图
        int n = s.length();
        HashMap<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < pairs.size(); i++) {
            int s1 = pairs.get(i).get(0);
            int s2 = pairs.get(i).get(1);
            if (!map.containsKey(s1)) map.put(s1, new ArrayList<>());
            if (!map.containsKey(s2)) map.put(s2, new ArrayList<>());
            map.get(s1).add(s2);
            map.get(s2).add(s1);
        }
        boolean[] visited = new boolean[n];
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> indexes = new ArrayList<>();
                List<Character> chs = new ArrayList<>();
                dfs(s, i, map, visited, indexes, chs);
                Collections.sort(indexes);
                Collections.sort(chs);
                for (int j = 0; j < indexes.size(); j++) {
                    res[indexes.get(j)] = chs.get(j);
                }
            }
        }
        return new String(res);
    }

    // 利用union find
    public String smallestStringWithSwaps2(String s, List<List<Integer>> pairs) {
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        int n = s.length();
        UnionFind uf = new UnionFind(n);
        for (List<Integer> pair : pairs) {
            int p = pair.get(0), q = pair.get(1);
            uf.union(p, q);
        }
        
        for (int i = 0; i < n; i++) {
            int root = uf.root(i);
            map.putIfAbsent(root, new PriorityQueue<>());
            map.get(root).offer(s.charAt(i));
        }
             
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int root = uf.root(i);
            sb.append(map.get(root).poll());
        }
    
        return sb.toString();
    }
}
