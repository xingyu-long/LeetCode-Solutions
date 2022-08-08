/*
 * @Date: 2020-03-24 13:37:37
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-24 13:40:53
 */
package com.leetcode.array.sort;

import java.util.*;

public class _1387_SortIntegersbyThePowerValue {
    public class Data {
        int num;
        int count;
        
        public Data(int n, int c) {
            num = n;
            count = c;
        }
    } 

    //time:O((hi - lo) * ways(< O(n)) + sort: nlogn) space:O(n)
    public int getKth(int lo, int hi, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Data> list = new ArrayList<>(hi - lo + 1);
        for (int i = 0; i < hi - lo + 1; i++) {
            list.add(new Data(lo + i, ways(lo + i, map)));
        }
        Collections.sort(list, (a, b) -> a.count - b.count);
        return list.get(k - 1).num;
    }
    
    public int ways(int num, HashMap<Integer, Integer> map) {
        if (num == 1) return 0;
        if (map.get(num) != null) return map.get(num);
        int res = 0;
        if (num % 2 == 0) {
            res = 1 + ways(num / 2, map);   
        } else {
            res = 1 + ways(3 * num + 1, map);     
        }
        map.put(num, res);
        return res;
    }
}