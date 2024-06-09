package com.pramp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class _Aug_29_2020_Word_Count_Engine {

    static String[][] wordCountEngine(String document) {
        // your code goes here
        // Map<String, Integer>
        // you'll
        // you ll
        // youll
        document = document.toLowerCase();
        String[] words = document.split("\\W+");
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        String[][] res = new String[map.size()][2];
        int j = 0;
        HashSet<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            if (set.add(words[i])) {
                res[j][0] = words[i];
                res[j][1] = "" + map.get(words[i]);
                j++;
            }
        }
        // sort;
        Arrays.sort(res, (a, b) -> (Integer.parseInt(b[1]) - Integer.parseInt(a[1])));
        return res;
    }

    public static void main(String[] args) {
        String s = "a b c d a d c";
        String[][] res = wordCountEngine(s);
        for (String[] each : res) {
            System.out.println(each[0] + " " + each[1]);
        }
    }
}
