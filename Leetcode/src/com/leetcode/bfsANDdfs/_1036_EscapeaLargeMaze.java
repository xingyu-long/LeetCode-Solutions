package com.leetcode.bfsANDdfs;

import java.util.HashSet;
import java.util.Set;

public class _1036_EscapeaLargeMaze {
    // 需要决定如何加速这个过程，应该是看lee215的帖子
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        // seach step by step
        // marked the blocked position.
        // How to search efficiently.
        Set<String> set = new HashSet<>();
        for (int[] b : blocked) {
            set.add(b[0] + "->" + b[1]);
        }

        return dfs(source[0], source[1], target, set, new HashSet<>()) &&
                dfs(target[0], target[1], source, set, new HashSet<>());
    }

    public boolean dfs(int x, int y, int[] target, Set<String> set, Set<String> visited) {
        if (x < 0 || x >= 1000000 || y < 0 || y >= 1000000) return false;
        String str = x + "->" + y;
        if (visited.contains(str) || set.contains(str)) return false;
        if (x == target[0] && y == target[1] ||
                visited.size() > 20000) return true;
        // 最大围成的面积。

        visited.add(str);

        boolean left = dfs(x, y - 1, target, set, visited);
        boolean right = dfs(x, y + 1, target, set, visited);
        boolean down = dfs(x + 1, y, target, set, visited);
        boolean up = dfs(x - 1, y, target, set, visited);

        return left || right || down || up;
    }

    public static void main(String[] args) {
        _1036_EscapeaLargeMaze escapeaLargeMaze = new _1036_EscapeaLargeMaze();
        int[] source = {0, 0}, target = {0, 2};
        int[][] blocked = {{0, 1}, {1, 0}};
        System.out.println(escapeaLargeMaze.isEscapePossible(blocked, source, target));
    }
}
