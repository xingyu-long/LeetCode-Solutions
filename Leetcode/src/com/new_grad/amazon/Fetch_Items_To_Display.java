package com.new_grad.amazon;

import java.util.*;

public class Fetch_Items_To_Display {
    static class PairInt {
        int first;
        int second;

        public PairInt() {
        }

        public PairInt(int f, int s) {
            first = f;
            second = s;
        }
    }

    static List<String> fetchItemsToDisplay(int numOfItems, HashMap<String, PairInt> items,
                                            int sortParameter, int sortOrder, int itemsPerPage, int pageNumber) {
        // 注意PQ会导致TLE的问题
        SortedSet<Map.Entry<String, PairInt>> set = new TreeSet<>(new Comparator<Map.Entry<String, PairInt>>() {
            @Override
            public int compare(Map.Entry<String, PairInt> o1, Map.Entry<String, PairInt> o2) {
                if (sortParameter == 0) {
                    if (sortOrder == 0) {
                        return o1.getKey().compareTo(o2.getKey());
                    }
                    return o2.getKey().compareTo(o1.getKey());
                }
                if (sortParameter == 1) {
                    if (sortOrder == 0) {
                        return o1.getValue().first - o2.getValue().first;
                    }
                    return o2.getValue().first - o1.getValue().first;
                }
                if (sortParameter == 2) {
                    if (sortOrder == 0) {
                        return o1.getValue().second - o2.getValue().second;
                    }
                    return o2.getValue().second - o1.getValue().second;
                }
                return 0;
            }
        });
        set.addAll(items.entrySet());
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, PairInt> entry : set) {
            res.add(entry.getKey());
        }
        int start = pageNumber * itemsPerPage;
        int end = Math.min((start + itemsPerPage), res.size());
        return res.subList(start, end);
    }

    public static void main(String[] args) {
        HashMap<String, PairInt> items = new HashMap<>();
        items.put("item1", new PairInt(10, 15));
        items.put("item2", new PairInt(3, 4));
        items.put("item3", new PairInt(17, 8));
        int numOfItems=3;
        int sortParameter = 0;
        int sortOrder = 0;
        int itemsPerPage = 2;
        int pageNumber = 1;
        List<String> res = fetchItemsToDisplay(3, items, sortParameter, sortOrder, itemsPerPage, pageNumber);
        System.out.println(res.toString());
    }
}
