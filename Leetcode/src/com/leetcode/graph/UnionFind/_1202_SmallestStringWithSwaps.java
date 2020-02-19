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
        int n = s.length();
        UnionFind unionFind = new UnionFind(n);
        for (List<Integer> pair : pairs) {
            unionFind.union(pair.get(0), pair.get(1));
        }
        HashMap<Integer, List<Character>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = unionFind.root(i);
            if (!map.containsKey(root)) map.put(root, new LinkedList<>());
            map.get(root).add(s.charAt(i));
        }
        // sort characters
        for (List<Character> characters : map.values()) {
            Collections.sort(characters);
        }
        char[] res = new char[n];
        for (int i = 0; i < n; i++) {
            List<Character> chs = map.get(unionFind.root(i));
            char currMin = chs.remove(0);
            res[i] = currMin;
        }
        return new String(res);
    }

    public static void main(String[] args) {
        String s = "udyyek";
        List<List<Integer>> pairs = new ArrayList<>();
        pairs.add(Arrays.asList(3, 3));
        pairs.add(Arrays.asList(3, 0));
        pairs.add(Arrays.asList(5, 1));
        pairs.add(Arrays.asList(3, 1));
        pairs.add(Arrays.asList(3, 4));
        pairs.add(Arrays.asList(3, 5));
        _1202_SmallestStringWithSwaps smallestStringWithSwaps = new _1202_SmallestStringWithSwaps();
        String res = smallestStringWithSwaps.smallestStringWithSwaps2(s, pairs);
        System.out.println(res);
    }
}
