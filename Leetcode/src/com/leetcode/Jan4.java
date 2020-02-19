package com.leetcode;

import java.util.*;

public class Jan4 {
    public String freqAlphabets(String s) {
        HashMap<String, Character> map = new HashMap<>();
        int j = 1;
        for (char i = 'a'; i <= 'i'; i++) {
            String key = "" + j;
            map.put(key, i);
            j++;
        }
        j = 10;
        for (char i = 'j'; i <= 'z'; i++) {
            String key = "" + j + "#";
            map.put(key, i);
            j++;
        }

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            int firstIndex = s.indexOf("#", i);
            if (firstIndex == -1 && i < s.length()) { // 全部是1-9以内
                char ch = s.charAt(i);
                sb.append(map.get(String.valueOf(ch)));
                i++;
            }
            if (firstIndex != -1) {
                while (i < firstIndex - 2) {
                    char ch = s.charAt(i);
                    sb.append(map.get(String.valueOf(ch)));
                    i++;
                }
                String temp = s.substring(firstIndex - 2, firstIndex + 1);
                sb.append(map.get(temp));
                i = firstIndex + 1;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        Jan4 test = new Jan4();
//        String s = "12345678910#11#12#13#14#15#16#17#18#19#20#21#22#23#24#25#26#";
        List<List<String>> watchedVideos = new ArrayList<>();
        watchedVideos.add(Arrays.asList("A", "B"));
        watchedVideos.add(Arrays.asList("C"));
        watchedVideos.add(Arrays.asList("B", "C"));
        watchedVideos.add(Arrays.asList("D"));
        int[][] friends = {{1,2}, {0,3}, {0,3}, {1,2}};
        int id = 0;
        int level = 2;
        List<String> res = test.watchedVideosByFriends(watchedVideos, friends, id, level);
        for (String str : res) {
            System.out.print(str + " ");
        }
    }


    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int level) {
        // graph
        List<HashSet<Integer>> adjs = new ArrayList<>();
        for (int i = 0; i < watchedVideos.size(); i++) {
            adjs.add(new HashSet<Integer>());
        }
        // 构建关系
        for (int i = 0; i < friends.length; i++) {
            int[] friend = friends[i];
            for (int j = 0; j < friends[i].length; j++) {
                adjs.get(i).add(friend[j]);
            }
        }
        boolean[] visited = new boolean[watchedVideos.size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        boolean isStart = false;
        HashSet<Integer> next = new HashSet<>();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                if (current == id) isStart = true;
                if (level == 0) next.add(current);
                for (int adj : adjs.get(current)) {
                    if (!visited[adj]) {
                        visited[adj] = true;
                        queue.offer(adj);
                    }
                }
            }
            if (isStart) level--;
        }
        
        List<String> res = new ArrayList<>();
        for (int in : next) {
            res.add("" + in);
        }
        return res;
    }
}
