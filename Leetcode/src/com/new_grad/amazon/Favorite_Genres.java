package com.new_grad.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Favorite_Genres {
    // 应该使用map来做
    // 记录一个songToGenres
    // 查找当前的喜欢的情况并且计数最大。
    public Map<String, List<String>> favoritegenre(
            Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
        Map<String, List<String>> res = new HashMap<>();
        Map<String, String> songToGenre = new HashMap<>();

        for (String genre : genreMap.keySet()) {
            List<String> songs = genreMap.get(genre);
            for (String song : songs) {
                songToGenre.put(song, genre);
            }
        }

        Map<String, Integer> count;
        int max;
        for (String user : userMap.keySet()) {
            count = new HashMap<>();
            max = 0;
            res.put(user, new ArrayList<>());

            List<String> songs = userMap.get(user);
            for (String song : songs) {
                String type = songToGenre.get(song);
                count.put(type, count.getOrDefault(type, 0) + 1);
                max = Math.max(max, count.get(type));
            }

            for (String str : count.keySet()) {
                if (count.get(str) == max) {
                    res.get(user).add(str);
                }
            }
        }
        return res;
    }
}