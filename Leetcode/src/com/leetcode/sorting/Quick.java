package com.leetcode.sorting;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quick<T extends Comparable<T>> extends Sort<T> {

    @Override
    public void sort(T[] nums) {
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
    }

    public void sort(T[] nums, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(nums, lo, hi);
        sort(nums, lo, j - 1);
        sort(nums, j + 1, hi);
    }
    public int partition(T[] nums, int lo, int hi) {
        int i = lo, j = hi + 1;
        T pivot = nums[lo];
        while (true) {
            while (less(nums[++i], pivot)) { // 找到第一个大于划分的v
                if (i == hi) break;
            }
            while (less(pivot, nums[--j])) { // 找到第一个小于划分的v
                if (j == lo) break;
            }
            if (i >= j) break;
            exch(nums, i, j);
        }
        exch(nums, lo, j); // j一定会走到小于v的划分那个位置。
        return j;
    }

    public void shuffle(T[] nums) {
        List<Comparable> list = Arrays.asList(nums);
        Collections.shuffle(list);
        list.toArray(nums);
    }

    public static void main(String[] args) {
        Quick<Integer> quick = new Quick<>();
        Integer[] nums = {5,4,3,2,1};
        quick.sort(nums);
        for (int in : nums) {
            System.out.print(in + " ");
        }
    }

}
