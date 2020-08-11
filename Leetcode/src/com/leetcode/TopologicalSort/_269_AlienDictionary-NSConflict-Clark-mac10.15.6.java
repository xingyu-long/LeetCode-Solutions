package com.leetcode.TopologicalSort;

import java.util.*;

/**
 * @Date: 2019/9/27, 05/10/2020
 * @Description: DP, word break
 **/
public class _269_AlienDictionary {

    // 1. 变量名别写错了！ 2. 检查入度的应该使用graph的keyset来看，因为indegree默认本身就是0.
    // 应该是新加了test case "abc" -> ""
    // https://www.youtube.com/watch?v=RIrTuf4DfPE
    // time : (V + E) -> O(n * words(max))
    // space : O(n) -> O(26) -> O(1)
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        int[] indegree = new int[26]; // 记录入度情况
        HashMap<Character, Set<Character>> graph = new HashMap<>();

        if (!buildGraph(words, indegree, graph)) {
            return "";
        }
        String res = bfs(indegree, graph);
        return res;
    }

    public boolean buildGraph(String[] words, int[] indegree,
        HashMap<Character, Set<Character>> graph) {
        // 构建对应的映射，但还没有加入实际的元素
        // ["z", "z"] -> "z"
        for (String word : words) {
            for (char c : word.toCharArray()) {
                // 新建对应的map
                graph.putIfAbsent(c, new HashSet<>());
            }
        }

        for (int i = 1; i < words.length; i++) {
            String first = words[i - 1];
            String second = words[i];
            // ["abc", "ab"] -> "";
            if (first.startsWith(second) && first.length() > second.length()) {
                return false;
            }
            int min = Math.min(first.length(), second.length());
            for (int j = 0; j < min; j++) {
                char charOne = first.charAt(j);
                char charTwo = second.charAt(j);
                if (charOne != charTwo) {
                    if (!graph.get(charOne).contains(charTwo)) {
                        graph.get(charOne).add(charTwo); // 构建了edge关系
                        indegree[charTwo - 'a']++;
                    }
                    break;
                }
            }
        }
        return true;
    }

    public String bfs(int[] indegree, HashMap<Character, Set<Character>> graph) {
        int total = graph.size();
        Queue<Character> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        // 先找indegree为0的情况
        for (char c : graph.keySet()) {
            if (indegree[c - 'a'] == 0) {
                queue.offer(c);
            }
        }

        // 开始遍历并且减-- 加入其它入度为0的char
        while (!queue.isEmpty()) {
            char cur = queue.poll();
            sb.append(cur);
            // System.out.println(sb.toString());
            for (char c : graph.get(cur)) {
                indegree[c - 'a']--;
                if (indegree[c - 'a'] == 0) {
                    queue.offer(c);
                }
            }
        }
        return sb.length() == total ? sb.toString() : "";
    }

    public static void main(String[] args) {
        _269_AlienDictionary dict = new _269_AlienDictionary();
        String[] words = {"abc", "ab"};
        System.out.println(dict.alienOrder(words));
    }
}
