package com.leetcode.design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _288_UniqueWordAbbreviation {
    Map<String, Set<String>> map;
    
    public _288_UniqueWordAbbreviation(String[] dictionary) {
        map = new HashMap<>();
        for (String word : dictionary) {
            String converted = convert(word);
            map.putIfAbsent(converted, new HashSet());
            map.get(converted).add(word);
        }
    }
    
    public boolean isUnique(String word) {
        String converted = convert(word);
        return !map.containsKey(converted) || (map.containsKey(converted) && map.get(converted).size() == 1 && map.get(converted).contains(word));
    }
    
    public String convert(String word) {
        int length = word.length();
        StringBuilder sb = new StringBuilder();
        if (length > 2) {
            sb.append(word.charAt(0));
            sb.append(length - 2);
            sb.append(word.charAt(length - 1));
            return sb.toString();
        } else {
            return word;
        }
    }
}
