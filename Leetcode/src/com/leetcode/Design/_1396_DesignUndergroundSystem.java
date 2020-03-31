/*
 * @Date: 2020-03-30 11:41:17
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-30 11:44:27
 */
package com.leetcode.Design;

import javafx.util.Pair;
import java.util.HashMap;


public class _1396_DesignUndergroundSystem {

    HashMap<Integer, Pair<String, Integer>> checkInMap;
    HashMap<Pair<String, String>, Pair<Double, Integer>> checkOutMap;
        
    public _1396_DesignUndergroundSystem() {
        checkInMap = new HashMap<>();
        checkOutMap = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Pair<>(stationName, t));
    }
    
    public void checkOut(int id, String stationName, int t) {
        Pair<String, Integer> prev = checkInMap.get(id);
        Pair<String, String> place = new Pair<>(prev.getKey(), stationName);
        double total = t - prev.getValue();
        if (checkOutMap.get(place) == null) {
            checkOutMap.put(place, new Pair<>(0.0, 0));
        }
        Pair<Double, Integer> data = checkOutMap.get(place);
        checkOutMap.put(place, new Pair<>(data.getKey() + total, data.getValue() + 1));
    }
    
    public double getAverageTime(String startStation, String endStation) {
        Pair<String, String> place  = new Pair<>(startStation, endStation);
        Pair<Double, Integer> data = checkOutMap.get(place);
        return data.getKey() / data.getValue();
    }
}