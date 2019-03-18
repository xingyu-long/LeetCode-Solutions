package com.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class ShortestWordDistance {

    /**
     * 243. Shortest Word Distance
     * When: 2019/03/18
     *
     * 思路：
     * solution1： 利用hashmap 记录每个单词的index 然后 两个for循环 比较index大小并且记得 abs 不然为负了。
     *
     * @param words
     * @param word1
     * @param word2
     * @return
     */
    public static int shortestDistance(String[] words, String word1, String word2){

        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < words.length; i++){
            map.put(i, words[i]);
        }
        ArrayList<Integer> list1 = getKey(map, word1);
        ArrayList<Integer> list2 = getKey(map, word2);

        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < list1.size(); i++)
            for (int j = 0; j < list2.size(); j++){
                int temp = Math.abs(list1.get(i) - list2.get(j));
                res.add(temp);
            }
        Collections.sort(res);
        System.out.println(list1.toString());
        System.out.println(list2.toString());
        return res.get(0);
    }

    public static ArrayList getKey(HashMap map, Object value){
        ArrayList<Integer> keyList = new ArrayList<>();
        for (Object key: map.keySet()){
            if (map.get(key).equals(value)){
                keyList.add((Integer) key);
            }
        }
        return keyList;
    }

    /**
     *
     * solution2: 依然是n*n 的解法，具体思路跟上面一致，只是更加简单
     */

    public static int shortestDistance2(String[] words, String word1, String word2){
        int res = words.length;
        for (int i = 0; i < words.length; i++){
            if (words[i].equals(word1)){
                for (int j = 0; j < words.length; j++){
                    if (words[j].equals(word2)){
                        res = Math.min(res, Math.abs(i - j));
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args){
        String[] words = new String[]{"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "coding";
        String word2 = "makes";
        System.out.println(shortestDistance(words, word1, word2));
        System.out.println(shortestDistance2(words, word1, word2));
    }
}
