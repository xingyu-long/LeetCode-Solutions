/*
 * @Date: 2020-03-24 12:05:46
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-24 13:18:44
 */
package com.leetcode.array;

import java.util.*;

public class _1386_CinemaSeatAllocation {
    // time: O(row * col)
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        if (reservedSeats == null || reservedSeats.length == 0 ||
           reservedSeats[0] == null || reservedSeats[0].length == 0) return 0;
        HashMap<Integer, HashSet<Integer>> filled = new HashMap<>();
        for (int[] seats : reservedSeats) {
            filled.putIfAbsent(seats[0], new HashSet<>());
            filled.get(seats[0]).add(seats[1]);
        }
        int res = (n - filled.size()) * 2;
        // postions we can place should be [2, 3, , 4, 5], [6, 7, , 8, 9], [4, 5, 6, 7]
        for (int row : filled.keySet()) {
            boolean placed = false;// 用来检查中间任意一个元素是否被占用
            if (!filled.get(row).contains(2) && !filled.get(row).contains(3) 
                && !filled.get(row).contains(4) && !filled.get(row).contains(5)){
                res++;
                placed = true;
            }
            
            if (!filled.get(row).contains(6) && !filled.get(row).contains(7) 
                && !filled.get(row).contains(8) && !filled.get(row).contains(9)){
                res++;
                placed = true;
            }
            
            // 检查中间的可能性
            if (!placed) {
                  if (!filled.get(row).contains(4) && !filled.get(row).contains(5) 
                      && !filled.get(row).contains(6) && !filled.get(row).contains(7)){
                      res++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] seats = {{1,2},{1,3},{1,8},{2,6},{3,1},{3,10}};
        _1386_CinemaSeatAllocation allocation = new _1386_CinemaSeatAllocation();
        System.out.println(allocation.maxNumberOfFamilies(n, seats));
    }
}