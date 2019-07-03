package com.leetcode.Design;

import java.util.HashMap;

public class _359_LoggerRateLimiter {


    /**
        359. Logger Rate Limiter
        When: 2019/7/3

     * Example:

     Logger logger = new Logger();

     // logging string "foo" at timestamp 1
     logger.shouldPrintMessage(1, "foo"); returns true;

     // logging string "bar" at timestamp 2
     logger.shouldPrintMessage(2,"bar"); returns true;

     // logging string "foo" at timestamp 3
     logger.shouldPrintMessage(3,"foo"); returns false;

     // logging string "bar" at timestamp 8
     logger.shouldPrintMessage(8,"bar"); returns false;

     // logging string "foo" at timestamp 10
     logger.shouldPrintMessage(10,"foo"); returns false;

     // logging string "foo" at timestamp 11
     logger.shouldPrintMessage(11,"foo"); returns true;
     * @param timestamp
     * @param message
     * @return
     */
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