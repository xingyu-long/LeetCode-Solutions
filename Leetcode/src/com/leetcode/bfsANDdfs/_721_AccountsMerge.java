package com.leetcode.bfsANDdfs;

import java.util.*;

/**
 * @Date: 05/13/2020
 * @Description: DFS, Union Find
 **/
public class _721_AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // 利用第一个邮箱和其他剩下的邮箱做无向图链接起来以及保持email对于name的对应
        Map<String, Set<String>> graph = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();

        for (List<String> account : accounts) {
            String name = account.get(0);
            for (int i = 1; i < account.size(); i++) {
                String email = account.get(i);
                emailToName.put(email, name);
                graph.putIfAbsent(email, new HashSet<>());
                if (i == 1) {
                    continue;
                }
                graph.get(account.get(i - 1)).add(email);
                graph.get(email).add(account.get(i - 1));
            }
        }
        HashSet<String> visited = new HashSet<>();
        List<List<String>> res = new LinkedList<>();
        // DFS
        for (String e : emailToName.keySet()) {
            List<String> list = new LinkedList<>();
            if (visited.add(e)) {
                dfs(graph, e, visited, list);
                Collections.sort(list);
                list.add(0, emailToName.get(e));
                res.add(list);
            }
        }
        return res;
    }

    private void dfs(Map<String, Set<String>> graph, String email, HashSet<String> visited,  List<String> list) {
        list.add(email);
        for (String adj : graph.get(email)) {
            if (visited.add(adj)) {
                dfs(graph, adj, visited, list);
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
                parent.put(email, email);// put themself first;
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
