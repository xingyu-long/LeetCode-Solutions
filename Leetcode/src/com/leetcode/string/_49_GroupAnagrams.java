/*
 * @Date: 08/11/2020 15:07:14
 * @LastEditTime: 08/12/2021 09:44:06
 * @Description: HashMap, Counting sort
 */
package com.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class _49_GroupAnagrams {

    // solution1: 使用sort来进行操作 time: O(n * nlogn) space:O(n)
    // 使用HashMap进行记录其排完序的str（数目肯定比strs少）key: str value: res.size()（这样方便记录）
    // 这种方法还不如直接最后添加呢
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0)
            return res;
        HashMap<String, Integer> map = new HashMap<>();

        for (String str : strs) {
            char[] arrStr = str.toCharArray();
            Arrays.sort(arrStr);
            String sortStr = new String(arrStr);
            if (map.containsKey(sortStr)) {
                // 表示已经有相同的排序元素了，然后使用map得到这个在res的坐标 然后插入这个list中
                List<String> list = res.get(map.get(sortStr));
                list.add(str);
            } else {
                // 一开始没有相同的元素下 开始的话 首先会创建一个list（用来存放新的array）然后将map作为标记（这里用size做index
                // 十分巧妙）然后使用res.add(list) 即加入
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sortStr, res.size());
                res.add(list);
            }
        }
        return res;
    }

    // counting sort 的思想 利用跟字符的相差来记录每个字符的个数并且进行类似排序操作
    // time: O(maxLen * n) space: O(n)
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if (strs == null || strs.length == 0)
            return res;
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] count = new int[26];
            for (char ch : str.toCharArray()) {
                count[ch - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                // 这里没有成功转为字符
                if (count[i] != 0) {
                    sb.append(count[i]);
                    sb.append((char) (i + 'a')); // count[i]和当前两个连在一起写的话，会以为是在相加，中间应该+""+
                }
            }
            // char[] chs = str.toCharArray();
            // Arrays.sort(chs); // 可以直接用排序的结果构造，这样看起来运行结果会快些，但是不一定。因为这里排序要nlogn
            String key = sb.toString();

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        res.addAll(map.values());
        return res;
    }
}
