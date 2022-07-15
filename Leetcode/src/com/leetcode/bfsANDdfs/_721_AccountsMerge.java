package com.leetcode.bfsANDdfs;

import java.util.*;

/**
 * @Date: 05/13/2020
 * @Description: DFS, Union Find
 **/
public class _721_AccountsMerge { 
    // time: O(# of emails)
    // space: O(# of emails)
    // 利用第一个邮箱和其他剩下的邮箱做无向图链接起来以及保持email对于name的对应
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 利用email来建立graph
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        for (List<String> acc : accounts) {
            int size = acc.size();
            String name = acc.get(0);
            for (int i = 1; i < size; i++) {
                String email = acc.get(i);
                emailToName.put(email, name);
                graph.putIfAbsent(email, new HashSet<>());
                if (i == 1) {
                    continue;
                }
                graph.get(email).add(acc.get(i - 1));
                graph.get(acc.get(i - 1)).add(email);
            }
        }
        List<List<String>> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String email : emailToName.keySet()) {
            List<String> list = new ArrayList<>();
            if (visited.contains(email)) continue;
            dfs(email, graph, visited, list);
            Collections.sort(list);
            list.add(0, emailToName.get(email));
            res.add(list);
        }
        return res;
    }
    
    public void dfs(String email, Map<String, Set<String>> graph, Set<String> visited, List<String> list) {
        if (visited.add(email)) {
            list.add(email);
            for (String next : graph.get(email)) {
                dfs(next, graph, visited, list);
            }
        }
    }

    public List<List<String>> accountsMerge2(List<List<String>> accounts) {
        // how to merge?
        if (accounts == null || accounts.size() == 0) {
            return new ArrayList<>();
        }
        Map<String, String> emailToName = new HashMap<>();
        Map<String, String> parent = new HashMap<>();
        Map<String, TreeSet<String>> union = new HashMap<>();
        // 保留email和name的关系，并且初始化parent数组为本身。
        for (List<String> acc : accounts) {
            String name = acc.get(0);
            for (int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                emailToName.put(email, name);
                parent.put(email, email);// put themselves first
            }
        }

        for (List<String> acc : accounts) {
            // 设每一个的第一个点为parent节点。
            String p = find(acc.get(1), parent);
            for (int i = 2; i < acc.size(); i++) {
                parent.put(find(acc.get(i), parent), p);
            }
        }

        // 需要注意这里的情况。
        for (List<String> acc : accounts) {
            // 先找到其parent节点，然后连接元素
            String p = find(acc.get(1), parent);
            if (!union.containsKey(p)) {
                union.put(p, new TreeSet<>());
            }
            // 包括其本身也要被加入。
            for (int i = 1; i < acc.size(); i++) {
                union.get(p).add(acc.get(i));
            }
        }

        List<List<String>> res = new ArrayList<>();
        for (String p : union.keySet()) {
            List<String> list = new ArrayList<>(union.get(p));
            list.add(0, emailToName.get(p));
            res.add(list);
        }
        return res;
    }

    public String find(String p, Map<String, String> parent) {
        while (!p.equals(parent.get(p))) {
            p = parent.get(p);
        }
        return p;
    }
}
