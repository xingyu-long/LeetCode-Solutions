package com.leetcode.stackPriorityQueue;

public class _1381_DesignaStackWithIncrementOperation {

    public int[] arr;
    public int size;
    public int max;
    /**
     * When:03/15/2020
     * @param maxSize
     */
    public _1381_DesignaStackWithIncrementOperation(int maxSize) {
        size = 0;
        max = maxSize;
        arr = new int[1001];
    }
    
    public void push(int x) {
        if (size < max) {
            arr[size++] = x;
        }
    }
    
    public int pop() {
        if (size < 1) return -1;
        int res = arr[size - 1];
        size--;
        return res;
    }
    
    public void increment(int k, int val) {
        int j = k < size ? k : size;
        for (int i = 0; i < j; i++) {
            arr[i] += val;
        }
    }
    // O(1) 
    /*
    int n;
    int[] inc;
    Stack<Integer> stack;
    public CustomStack(int maxSize) {
        n = maxSize;
        inc = new int[n];
        stack = new Stack<>();
    }
    
    public void push(int x) {
        if (stack.size() < n) {
            stack.push(x);
        } 
    }
    
    public int pop() {
        int i = stack.size() - 1;
        if (i < 0)  return -1;
        if (i > 0) inc[i - 1] += inc[i];
        int res = stack.pop() + inc[i];
        inc[i] = 0;
        return res;
    }
    
    public void increment(int k, int val) {
        int i = Math.min(k, stack.size()) - 1;
        if (i >= 0) 
            inc[i] += val;
    }
    
    */
}