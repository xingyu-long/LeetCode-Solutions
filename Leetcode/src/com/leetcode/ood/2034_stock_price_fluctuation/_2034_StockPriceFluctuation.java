/*
 * @Date: 07/17/2022 21:11:04
 * @LastEditTime: 07/17/2022 21:11:05
 * @Description: You need to fill out
 */
package com.leetcode.ood;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

public class _2034_StockPriceFluctuation {
    // time to price (latest)
    TreeMap<Integer, Integer> record;
    TreeMap<Integer, Set<Integer>> priceToTime;
    
    public _2034_StockPriceFluctuation() {
        record = new TreeMap<>();
        priceToTime = new TreeMap<>();
    }
    
    public void update(int timestamp, int price) {
        if (record.containsKey(timestamp)) {
            // remove the old one
            int oldPrice = record.get(timestamp);
            priceToTime.get(oldPrice).remove(timestamp);
            if (priceToTime.get(oldPrice).isEmpty()) {
                priceToTime.remove(oldPrice);
            }
        }
        record.put(timestamp, price);
        priceToTime.putIfAbsent(price, new HashSet<>());
        priceToTime.get(price).add(timestamp);
    }
    
    public int current() {
        return record.get(record.lastKey());
    }
    
    public int maximum() {
        return priceToTime.lastKey();
    }
    
    public int minimum() {
        return priceToTime.firstKey();
    }
}
