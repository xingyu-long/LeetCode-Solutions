package com;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class test {
    public static String getHint(String secret, String guess) {
        if (secret == null || guess == null) return "0A0B";
        HashMap<Character, Set<Character>> map = new HashMap<>();
        // 存char对应的index集合
        for (int i = 0; i < secret.length(); i++) {
            char ch = secret.charAt(i);
            if (!map.containsKey(ch)) {
                HashSet<Character> set = new HashSet<>();
                set.add((char) i);
                map.put(ch, set);
            } else {
                map.get(ch).add((char) i);
            }
        }
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < guess.length(); i++) {
            char ch = guess.charAt(i);
            if (map.containsKey(ch)) {
                Set<Character> set = map.get(ch);
                if (set.contains((char) i)) {
                    bulls++;
                    map.get(ch).remove((char) i);
                } else {
                    cows++;
                    // 随便删除一个
                    Iterator<Character> iterator = set.iterator();
                    map.get(ch).remove(iterator.next());
                    if (map.get(ch).isEmpty()) {
                        map.remove(ch);
                    }
                }
            }
        }
        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        System.out.println(getHint("1123", "0111"));
    }
}
