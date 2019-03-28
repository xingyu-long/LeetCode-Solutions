package com.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class _49_GroupAnagrams {

    /**
     * 49. Group Anagrams
     * When: 2019/03/28
     *
     * solution:
     *
     * 涉及到的数据结构：
     * ArrayList<>(), Arrays.sort(), map.containsKey(),list.add
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        /** solution1: 使用sort来进行操作  time: O(m * n * nlogn) space:O(n)
         * 使用HashMap进行记录其排完序的str（数目肯定比strs少）key: str value: res.size()（这样方便记录）
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();

        for (String str: strs){
            char[] arrStr = str.toCharArray();
            Arrays.sort(arrStr);
            String sortStr = new String(arrStr);
            if (map.containsKey(sortStr)){
                // 表示已经有相同的排序元素了，然后使用map得到这个在res的坐标 然后插入这个list中
                List<String> list = res.get(map.get(sortStr));
                list.add(str);
            } else {
                // 一开始没有相同的元素下 开始的话 首先会创建一个list（用来存放新的array）然后将map作为标记（这里用size做index 十分巧妙）然后使用res.add(list) 即加入
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sortStr, res.size());
                res.add(list);
            }
        }
        return res;
         **/

        /**
         * counting sort 的思想 利用跟字符的相差来记录每个字符的个数并且进行类似排序操作
         * time: O(m * n) space: O(n)
         */
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str: strs) {
            int[] count = new int[26]; // 用来记录每个单词对应的个数
            for (Character ch: str.toCharArray()) {
                count[ch - 'a']++;
            }
            String s = "";
            for (int i = 0; i < count.length; i++) {
                if (count[i] != 0) {
                    s += String.valueOf(count[i]) + String.valueOf((char)(i + 'a'));
                }
            }

            if (map.containsKey(s)) {
                List<String> list = map.get(s);
                list.add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(s, list);
            }
        }
        return new ArrayList<>(map.values());

    }
}
