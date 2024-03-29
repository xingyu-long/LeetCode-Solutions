/*
 * @Date: 07/24/2022 10:13:57
 * @LastEditTime: 07/24/2022 10:13:57
 * @Description: You need to fill out
 */
package com.leetcode.ood;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class _2353_DesignaFoodRatingSystem {
    Map<String, TreeMap<Integer, TreeSet<String>>> map;
    Map<String, Integer> foodToRating;
    Map<String, String> foodToCuisine;

    public _2353_DesignaFoodRatingSystem(String[] foods, String[] cuisines, int[] ratings) {
        map = new HashMap<>();
        foodToRating = new HashMap<>();
        foodToCuisine = new HashMap<>();
        for (int i = 0; i < foods.length; i++) {
            String food = foods[i], cuisine = cuisines[i];
            int rating = ratings[i];
            map.putIfAbsent(cuisine, new TreeMap<>());
            map.get(cuisine).putIfAbsent(rating, new TreeSet<>());
            map.get(cuisine).get(rating).add(food);
            foodToRating.put(food, rating);
            foodToCuisine.put(food, cuisine);
        }
    }

    public void changeRating(String food, int newRating) {
        int prevRating = foodToRating.get(food);
        String cuisine = foodToCuisine.get(food);
        map.get(cuisine).get(prevRating).remove(food);
        if (map.get(cuisine).get(prevRating).isEmpty()) {
            map.get(cuisine).remove(prevRating);
        }
        map.get(cuisine).putIfAbsent(newRating, new TreeSet<>());
        map.get(cuisine).get(newRating).add(food);
        foodToRating.put(food, newRating);
    }

    public String highestRated(String cuisine) {
        TreeMap<Integer, TreeSet<String>> tm = map.get(cuisine);
        return tm.get(tm.lastKey()).first();
    }
}
