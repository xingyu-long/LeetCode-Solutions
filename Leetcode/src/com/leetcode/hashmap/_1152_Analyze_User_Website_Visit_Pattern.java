package com.leetcode.hashmap;

import java.util.*;

public class _1152_Analyze_User_Website_Visit_Pattern {
    private static class Pair {
        int time;
        String web;

        public Pair(int t, String w) {
            time = t;
            web = w;
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair>> map = new HashMap<>();
        int n = username.length;
        for (int i = 0; i < n; i++) {
            map.putIfAbsent(username[i], new ArrayList<>());
            map.get(username[i]).add(new Pair(timestamp[i], website[i]));
        }

        Map<String, Integer> count = new HashMap<>();
        String freqStr = "";
        for (String user : map.keySet()) {
            Set<String> set = new HashSet<>();
            List<Pair> list = map.get(user);
            Collections.sort(list, (a, b) -> (a.time - b.time));
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    for (int k = j + 1; k < list.size(); k++) {
                        String seq = list.get(i).web + " " + list.get(j).web + " " + list.get(k).web;
                        if (set.contains(seq)) continue;
                        count.put(seq, count.getOrDefault(seq, 0) + 1);
                        set.add(seq);
                        if (freqStr.equals("") || count.get(seq) > count.get(freqStr) ||
                                (count.get(seq) == count.get(freqStr) && seq.compareTo(freqStr) < 0)) {
                            freqStr = seq;
                        }
                    }
                }
            }
        }
        List<String> res = new ArrayList<>();
        Collections.addAll(res, freqStr.split("\\W+"));
        return res;
    }
}
