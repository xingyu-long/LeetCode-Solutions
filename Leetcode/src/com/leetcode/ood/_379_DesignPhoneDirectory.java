package com.leetcode.ood;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class _379_DesignPhoneDirectory {
    /**
     *  379. Design Phone Directory
     *  When: 2019/7/6
     * Design a Phone Directory which supports the following operations:

     get: Provide a number which is not assigned to anyone.
     check: Check if a number is available or not.
     release: Recycle or release a number.
     Example:

     // Init a phone directory containing a total of 3 numbers: 0, 1, and 2.
     PhoneDirectory directory = new PhoneDirectory(3);

     // It can return any available phone number. Here we assume it returns 0.
     directory.get();

     // Assume it returns 1.
     directory.get();

     // The number 2 is available, so return true.
     directory.check(2);

     // It returns 2, the only number that is left.
     directory.get();

     // The number 2 is no longer available, so return false.
     directory.check(2);

     // Release number 2 back to the pool.
     directory.release(2);

     // Number 2 is available again, return true.
     directory.check(2);

     * @param maxNumbers
     */

    Queue<Integer> pool;
    Set<Integer> used;
    int maxNumbers;
    
    public _379_DesignPhoneDirectory(int maxNumbers) {
        pool = new LinkedList<>();
        used = new HashSet<>();
        this.maxNumbers = maxNumbers;
        for (int i = 0; i < maxNumbers; i++) {
            pool.offer(i);
        }
    }
    
    public int get() {
        Integer res = pool.poll();
        if (res == null) {
            return -1;
        }
        used.add(res);
        return res;
    }
    
    public boolean check(int number) {
        if (number > maxNumbers || number < 0) {
            return false;
        }
        return !used.contains(number);
    }
    
    public void release(int number) {
        if (used.remove(number)) {
            pool.offer(number);
        }
    }
}
