package com.leetcode.Design;

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

    private Iterator<Integer> iter;
    private Integer next = null;

    public _284_PeekingIterator(Iterator<Integer> iterator) {
        iter = iterator;
        if (iter.hasNext()) {
            next = iter.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    public Integer next() {
        Integer res = next;
        next = iter.hasNext() ? iter.next() : null;
        return res;
    }

    public boolean hasNext() {
        return next != null;
    }
}
