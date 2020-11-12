package com.leetcode.design;

import java.util.HashMap;

public class _288_UniqueWordAbbreviation {
    /**
     * 288. Unique Word Abbreviation
     * When:2019/7/5

     * An abbreviation of a word follows the form <first letter><number><last letter>.
      Below are some examples of word abbreviations:

     a) it                      --> it    (no abbreviation)

     1
     b) d|o|g                   --> d1g

     1    1  1
     1---5----0----5--8
     c) i|nternationalizatio|n  --> i18n

     1
     1---5----0
     d) l|ocalizatio|n          --> l10n
     Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary.
     A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

     Example:
     Given dictionary = [ "deer", "door", "cake", "card" ] "make"

     isUnique("dear") -> false
     isUnique("cart") -> true
     isUnique("cane") -> false
     isUnique("make") -> true


     * @param dictionary
     */

    HashMap<String, String> map;

    //time: O(n) space:O(n)
    public _288_UniqueWordAbbreviation(String[] dictionary) {
        map = new HashMap<>();
        for (String word : dictionary) {
            String key = getKey(word); // get the abbreviation
            if (map.containsKey(key)) {
                if (!map.get(key).equals(word)) {
                    map.put(key, "");
                }
            } else {
                map.put(key, word);
            }
        }
    }

    //第二个判断是否unique （单词缩写和单词相同就是unique）这个题意需要再好好审审
    public boolean isUnique(String word) {
        return !map.containsKey(getKey(word)) || map.get(getKey(word)).equals(word);
    }

    public String getKey(String s) {
        if (s.length() <= 2) return s;
        return s.charAt(0) + Integer.toString(s.length() - 2) + s.charAt(s.length() - 1);
    }
}
