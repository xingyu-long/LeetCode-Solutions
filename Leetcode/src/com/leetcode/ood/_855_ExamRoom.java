package com.leetcode.ood;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date: 06/05/2020
 * @Description: TODO
 **/
public class _855_ExamRoom {

    private int n;
    private List<Integer> list;

    public _855_ExamRoom(int N) {
        n = N;
        list = new ArrayList<>();
    }

    public int seat() {
        if (list.size() == 0) {
            list.add(0);
            return 0;
        }
        // 先检查两端的大小
        int max = Math.max(list.get(0), n - 1 - list.get(list.size() - 1));
        // 去看部分是否有更大的情况
        for (int i = 1; i < list.size(); i++) {
            max = Math.max(max, (list.get(i) - list.get(i - 1)) / 2);
        }
        // important， [0,0,0,0,0,0,1]
        if (list.get(0) == max) {
            list.add(0, 0);
            return 0;
        }
        // insert into current seats
        for (int i = 1; i < list.size(); i++) {
            if ((list.get(i) - list.get(i - 1)) / 2 == max) {
                list.add(i, (list.get(i) + list.get(i - 1)) / 2);
                return list.get(i);
            }
        }

        // otherwise, we put it in the end
        list.add(n - 1);
        return n - 1;
    }

    public void leave(int p) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == p) {
                list.remove(i);
            }
        }
    }
}
