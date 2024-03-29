package com.leetcode.bfs_and_dfs;

import java.util.HashSet;
import java.util.Set;

public class _753_CrackingtheSafe {

    // time:O(k^n) space:O(k^n)
    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int total = (int) Math.pow(k, n);
        for (int i = 0; i < n; i++) sb.append("0"); // 初始化的

        Set<String> visited = new HashSet<>();
        visited.add(sb.toString());

        dfs(sb, total, visited, n, k);

        return sb.toString();
    }

    public boolean dfs(StringBuilder sb, int total, Set<String> visited, int n, int k) {
        if (visited.size() == total) return true;
        String prev = sb.substring(sb.length() - n + 1);
        for (int i = 0; i < k; i++) {
            String next = prev + i;
            if (!visited.contains(next)) {
                visited.add(next);
                sb.append(i);
                if (dfs(sb, total, visited, n, k)) return true;
                visited.remove(next);
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        return false;
    }
}
