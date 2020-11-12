package com.leetcode.design;

public class _900_RLEIterator {
    // 这个设计的太妙了！
    int[] arr;
    int index;
    public _900_RLEIterator(int[] A) {
        this.arr = A;
        index = 0;
    }

    public int next(int n) {
        while (index < arr.length && n > arr[index]) { // 当n比当前的count数大的时候就一直减吧。
            n -= arr[index];
            index += 2;
        }
        if (index < arr.length) {
            arr[index] -= n;
            return arr[index + 1];
        }
        return -1;
    }
}
