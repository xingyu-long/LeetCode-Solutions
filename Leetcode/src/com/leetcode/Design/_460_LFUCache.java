package com.leetcode.design;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @Date: 09/28/2020, 10/26/2020
 * @Description: Design, LinkedHashSet
 **/
public class _460_LFUCache {
    Map<Integer, Integer> map;
    Map<Integer, LinkedHashSet<Integer>> freqToKey;
    Map<Integer, Integer> keyToFreq;

    int size;
    int min;

    public _460_LFUCache(int capacity) {
        map = new HashMap<>();
        freqToKey = new HashMap<>();
        keyToFreq = new HashMap<>();
        // 先预备一个，放在这。
        freqToKey.put(1, new LinkedHashSet<>());

        size = capacity;
        min = -1;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        int freq = keyToFreq.get(key);
        keyToFreq.put(key, freq + 1);
        freqToKey.get(freq).remove(key);
        if (freq == min && freqToKey.get(freq).size() == 0) {
            min++;
        }
        freqToKey.putIfAbsent(freq + 1, new LinkedHashSet<>());
        freqToKey.get(freq + 1).add(key);
        return map.get(key);
    }

    public void put(int key, int value) {
        if (size <= 0) {
            return;
        }

        if (map.containsKey(key)) {
            map.put(key, value);
            get(key);
        } else {
            if (map.size() >= size) {
                int removedKey = freqToKey.get(min).iterator().next();
                freqToKey.get(min).remove(removedKey);
                map.remove(removedKey);
                keyToFreq.remove(removedKey);
            }

            map.put(key, value);
            keyToFreq.put(key, 1);
            min = 1;
            freqToKey.get(min).add(key);
        }
    }
}
