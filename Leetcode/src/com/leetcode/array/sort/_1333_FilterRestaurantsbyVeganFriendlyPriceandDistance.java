package com.leetcode.array.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class _1333_FilterRestaurantsbyVeganFriendlyPriceandDistance {
    public class Restaurant implements Comparable<Restaurant>{
        int id;
        int rating;
        int veganFriendly;
        int price;
        int distance;

        public Restaurant(int i, int r, int v, int p, int d) {
            id = i;
            rating = r;
            veganFriendly = v;
            price = p;
            distance = d;
        }

        @Override
        public int compareTo(Restaurant o) {
            if (this.rating != o.rating) return o.rating - this.rating;
            return o.id - this.id;
        }
    }
    // time:O(nlogn) space:O(n)
    public List<Integer> filterRestaurants(int[][] restaurants, int veganFriendly, int maxPrice, int maxDistance) {
        if (restaurants == null || restaurants.length == 0 ||
                restaurants[0] == null || restaurants[0].length == 0)
            return new ArrayList<>();
        List<Restaurant> lists = new ArrayList<>();
        for (int[] row : restaurants) {
            if (row[3] <= maxPrice && row[4] <= maxDistance && (veganFriendly == 0 || row[2] == veganFriendly))
                lists.add(new Restaurant(row[0], row[1], row[2], row[3], row[4]));
        }
        Collections.sort(lists);
        List<Integer> res = new ArrayList<>();
        for (Restaurant list : lists) {
            res.add(list.id);
        }
        return res;
    }
}
