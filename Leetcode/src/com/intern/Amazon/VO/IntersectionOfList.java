package com.intern.Amazon.VO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class IntersectionOfList {
    // 两个list的交集。 利用HashSet
    // 多个list的交集，利用两个HashMap，每次就会取相同的最小的情况。
    // "我的想法是List转set，然后用divide and conquer两两用retainAll（java）求并集"

    // 利用divide and conquer.
    public List<Integer> intersection (List<List<Integer>> lists) {
        return split(lists, 0, lists.size() - 1);
    }

    public List<Integer> split(List<List<Integer>> lists, int left, int right) {
        if (left >= right) return lists.get(left);

        int mid = left + (right - left) / 2;
        List<Integer> l = split(lists, left, mid);
        List<Integer> r = split(lists, mid + 1, right);
        return mergeCommon(l, r);
    }

    public List<Integer> mergeCommon(List<Integer> p, List<Integer> q) {
        if (p == null || q == null) return new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> ret = new HashSet<>();
        for (int num : p) {
            set.add(num);
        }
        for (int num : q) {
            if (set.contains(num)) {
                ret.add(num);
            }
        }
        List<Integer> res = new ArrayList<>(ret);
        return res;
    }

    public static void main(String[] args) {
        IntersectionOfList intersectionOfList = new IntersectionOfList();
        List<Integer> p = new ArrayList<>(Arrays.asList(1,2,2,3,4));
        List<Integer> q = new ArrayList<>(Arrays.asList(2,3,4,5,1));
        List<Integer> r = new ArrayList<>(Arrays.asList(20,3,4,5));
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(p);
        lists.add(r);
        lists.add(q);
        System.out.println(intersectionOfList.intersection(lists));
    }
}
