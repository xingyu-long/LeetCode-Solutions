package com.leetcode.ood;

import java.util.Iterator;

public class _284_PeekingIterator {

    /**
     * 284. Peeking Iterator
     * When: 2019/7/4

     Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

     Call next() gets you 1, the first element in the list.

     Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

     You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.


     * @param iterator
     */

    // amortize O(1)
    // 相当于提前把next缓存出来
    Iterator<Integer> iterator;
    Integer next;
    public _284_PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        if (iterator.hasNext()) {
            next = iterator.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    public Integer next() {
        Integer res = next;
        if (iterator.hasNext()) {
            next = iterator.next();
        } else {
            next = null;
        }
        return res;
    }

    public boolean hasNext() {
        return next != null;
    }
}
