package com.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class _609_FindDuplicateFileinSystem {
    /**
     * 609. Find Duplicate File in System
     * When: 11/10/2019
     * 利用hashmap存文件内容和地址的映射，值得注意的是找duplicate所以需要 > 1
     * @param paths
     * @return
     */
    // 值得看的 follow up: https://leetcode.com/problems/find-duplicate-file-in-system/discuss/104120/Follow-up-questions-discussion
    // 利用MD5来判断是否重复
    public List<List<String>> findDuplicate(String[] paths) {
        List<List<String>> res = new ArrayList<>();
        if (paths == null || paths.length == 0) return res;
        HashMap<String, List<String>> map = new HashMap<>();
        // "root/a 1.txt(abcd) 2.txt(abcd)"
        for (String path : paths) {
            String[] strs = path.split("\\s+");// split by space;
            for (int i = 1; i < strs.length; i++) {
                int posOfLeft = strs[i].indexOf("(");
                String content = strs[i].substring(posOfLeft);
                if (!map.containsKey(content)) {
                    List<String> list = new ArrayList<>();
                    list.add(strs[0] + "/" + strs[i].substring(0, posOfLeft));
                    map.put(content, list);
                } else {
                    map.get(content).add(strs[0] + "/" + strs[i].substring(0, posOfLeft));
                }
            }
        }
        for (String key : map.keySet()) {
            if (map.get(key).size() > 1) { // find the duplicate
                res.add(new ArrayList<>(map.get(key)));
            }
        }
        return res;
    }
}
