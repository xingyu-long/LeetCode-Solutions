package com.leetcode.design;

import java.util.HashMap;

/**
 * @Date: 09/26/2020
 * @Description: HashMap
 **/
public class _359_LoggerRateLimiter {

    //time:O(1) space:O(n)
    public boolean shouldPrintMessage(int timestamp, String message) {
        HashMap<String, Integer> map = new HashMap<>();
        if (!map.containsKey(message) || timestamp - map.get(message) >= 10) {
            map.put(message, timestamp);
            return true;
        }
        return false;
    }
}