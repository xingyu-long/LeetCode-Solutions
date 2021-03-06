package com.leetcode.design;

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

    HashSet<Integer> used = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    int maxNumbers;

    public _379_DesignPhoneDirectory(int maxNumbers) {
        this.maxNumbers = maxNumbers;
        for (int i = 0; i < maxNumbers; i++) {
            queue.offer(i);
        }
    }

    public int get() {
        Integer res = queue.poll();
        if (res == null) {
            return -1;
        }
        used.add(res);
        return res;
    }

    public boolean check(int number) {
        if (number >= maxNumbers || number < 0) {
            return false;
        }
        return !used.contains(number);
    }

    public void release(int number) {
        if (used.remove(number)) {
            queue.offer(number);
        }
    }

    Set<Integer> available;
    /** Initialize your data structure here
     @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
//    public _379_DesignPhoneDirectory2(int maxNumbers) {
//        available = new HashSet<>();
//        for (int i = 0; i < maxNumbers; i++) {
//            available.add(i);
//        }
//    }

    /** Provide a number which is not assigned to anyone.
     @return - Return an available number. Return -1 if none is available. */
    public int get2() {
        if (available.isEmpty()) return -1;
        int newNumber = available.iterator().next();
        available.remove(newNumber);
        return newNumber;
    }

    /** Check if a number is available or not. */
    public boolean check2(int number) {
        return available.contains(number);
    }

    /** Recycle or release a number. */
    public void release2(int number) {
        available.add(number);
    }
}
