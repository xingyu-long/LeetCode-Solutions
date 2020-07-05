package com.leetcode.bfsANDdfs;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
}
