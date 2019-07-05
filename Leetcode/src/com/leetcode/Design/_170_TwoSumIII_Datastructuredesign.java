package com.leetcode.Design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    private List<Integer> list;
    private HashMap<Integer, Integer> map;

    public void TwoSum() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    public void add2(int number) {
        if (!map.containsKey(number)) {
            map.put(number, 1);
            list.add(number);
        } else {
            map.put(number, map.get(number) + 1); // maybe there have duplicates
        }
    }

    public boolean find2(int value) {
        for (Integer num1 : list) {
            int num2 = value - num1;
            // two same nums OR different nums
            if ((num1 == num2) && map.get(num1) > 1 || (num1 != num2 && map.containsKey(num2))) {
                return true;
            }
        }
        return false;
    }

}
