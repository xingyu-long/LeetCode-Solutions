package com.leetcode.ood;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _170_TwoSumIII_Datastructuredesign {

    /**
     * 170. Two Sum III - Data structure design
     * When: 2019/7/5
     * Design and implement a _1_TwoSum class. It should support the following operations: add and find.

     add - Add the number to an internal data structure.
     find - Find if there exists any pair of numbers which sum is equal to the value.

     For example,
     add(1); add(3); add(5);
     find(4) -> true
     find(7) -> false

     */

    Map<Integer, Integer> map;

    /** Initialize your data structure here. */
    public _170_TwoSumIII_Datastructuredesign() {
        map = new HashMap<>();
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        map.put(number, map.getOrDefault(number, 0) + 1);
    }

    /** Find if there exists any pair of numbers which sum is equal to the value. */
    public boolean find(int value) {
        for (int num1 : map.keySet()) {
            int num2 = value - num1;
            if ((num1 == num2) && map.get(num1) > 1 || (num1 != num2) && map.containsKey(num2)) {
                return true;
            }
        }
        return false;
    }

}
