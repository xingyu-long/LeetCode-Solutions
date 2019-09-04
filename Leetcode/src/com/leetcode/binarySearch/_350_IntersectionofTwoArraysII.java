package com.leetcode.binarySearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class _350_IntersectionofTwoArraysII {

    /**
     *  350. Intersection of Two Arrays II
     *  When: 2019/7/20
     *  Difficulty: Easy
     * @param nums1
     * @param nums2
     * @return
     */
    // hashMap
    public int[] intersect2(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            if (map.containsKey(nums1[i])) {
                map.put(nums1[i], map.get(nums1[i]) + 1);
            } else {
                map.put(nums1[i], 1);
            }
        }
        // 循环查询
        for (int i = 0; i < nums2.length; i++) {
            if (map.containsKey(nums2[i])) {
                if (map.get(nums2[i]) > 0) {
                    list.add(nums2[i]);
                    map.put(nums2[i], map.get(nums2[i]) - 1);
                }
            }
        }
        int[] res = new int[list.size()];
        int k = 0;
        for (Integer num : list) {
            res[k++] = num;
        }
        return res;
    }

    // two pointer
    public int[] intersect(int[] nums1, int[] nums2) {
        // 排序然后遍历
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;
        List<Integer> list = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] res = new int[list.size()];
        int k = 0;
        for (Integer in : list) {
            res[k++] = in;
        }
        return res;
    }

}
