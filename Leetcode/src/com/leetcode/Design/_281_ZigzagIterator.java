package com.leetcode.Design;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class _281_ZigzagIterator {

    /**
     * 281. Zigzag Iterator
     * When: 2019/7/4
     * For example, given two 1d vectors:
     * <p>
     * v1 = [1, 2]
     * v2 = [3, 4, 5, 6]
     * <p>
     * output : 1 3 2 4 5 6
     * <p>
     * [1,2,3]
     * [4,5,6,7]
     * [8,9]
     * It should return [1,4,8,2,5,9,3,6,7].
     * <p>
     * time : O(n)
     * space : O(1)
     Test case:
     j  v1 = [1, 2]
     i  v2 = [3, 4, 5, 6]

     j = 1存在，交换
     i  v1 = [1, 2]
     j  v2 = [3, 4, 5, 6]
     弹出v1.next() (1) res = [1, ];
     i  v1 = [2]
     j  v2 = [3, 4, 5, 6]

     j = 3存在，交换
     j  v1 = [2]
     i  v2 = [3, 4, 5, 6]
     弹出v2.next() (3) res = [1, 3, ];
     j  v1 = [2]
     i  v2 = [4, 5, 6]

     j = 2存在，交换
     i  v1 = [2]
     j  v2 = [4, 5, 6]
     弹出v1.next() (2) res = [1, 3, 2, ];
     i  v1 = []
     j  v2 = [4, 5, 6]

     j = 4存在，交换
     j  v1 = []
     i  v2 = [4, 5, 6]
     弹出v2.next() (4) res = [1, 3, 2, 4, ];
     j  v1 = []
     i  v2 = [5, 6]

     j 不存在
     弹出v2.next() (5) res = [1, 3, 2, 4, 5, ];

     j 不存在
     弹出v2.next() (6) res = [1, 3, 2, 4, 5, 6];
     * @param v1
     * @param v2
     */

    private Iterator<Integer> i, j, temp;

    public _281_ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        i = v2.iterator();
        j = v1.iterator();
    }

    public int next() {
        if (j.hasNext()) {
            temp = j;
            j = i;
            i = temp;
        }
        return i.next();
    }

    public boolean hasNext() {
        return i.hasNext() || j.hasNext();
    }

    /** solution for k vectors */
    LinkedList<Iterator> list;

    public void ZigzagIterator2(List<Integer> v1, List<Integer> v2) {
        list = new LinkedList<>();
        if (!v1.isEmpty()) list.add(v1.iterator());
        if (!v2.isEmpty()) list.add(v2.iterator());
    }

    public int next2() {
        Iterator poll = list.remove();
        int res = (Integer) poll.next();
        if (poll.hasNext()) {
            list.add(poll);
        }
        return res;
    }

    public boolean hasNext2() {
        return !list.isEmpty();
    }

}
