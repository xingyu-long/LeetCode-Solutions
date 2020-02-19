package com.leetcode.sorting;

public class Merge <T extends Comparable<T>> extends Sort<T> {

    protected T[] aux;

    @Override
    public void sort(T[] nums) {
        aux = (T[]) new Comparable[nums.length];
        sort(nums, 0, nums.length - 1);
    }

    private void sort(T[] nums, int l, int r) {
        if (l >= r) return;
        int mid = l + (r - l) / 2;
        sort(nums, l, mid);
        sort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }
    public void merge(T[] nums, int l, int m, int r) {
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++) {
            aux[k] = nums[k];
        }

        for (int k = l; k <= r; k++) {
            if (i > m) { // 表示左半部分走完了 把右边全部加入
                nums[k] = aux[j++];
            } else if (j > r) {
                nums[k] = aux[i++];
            } else if (aux[i].compareTo(aux[j]) <= 0) {
                nums[k] = aux[i++];
            } else {
                nums[k] = aux[j++];
            }
        }
    }

    public static void main(String[] args) {
        Merge<Integer> merge = new Merge<>();
        Integer[] nums = {5, 4, 3, 2, 1};
        merge.sort(nums);
        for (int in : nums) {
            System.out.print(in + " ");
        }
    }
}
