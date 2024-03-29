/*
 * @Date: 07/17/2022 20:39:37
 * @LastEditTime: 07/17/2022 20:42:09
 * @Description: You need to fill out
 */
package com.leetcode.array.interval;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class _2158_AmountofNewAreaPaintedEachDay {
    public class Node {
        int index;
        boolean isEnter;

        public Node(int index, boolean isEnter) {
            this.index = index;
            this.isEnter = isEnter;
        }
    }

    // time: O(max * logN)
    public int[] amountPainted(int[][] paint) {
        // something similar to meeting room?
        // but it seems we don't need to sort
        int n = paint.length;
        int max = 0;
        for (int[] p : paint) {
            max = Math.max(max, p[1]);
        }
        // 对于每一个点都建立一个list存储访问的情况（可能出现上一个节点在这里结束，下一个节点在这里开始）
        List<Node>[] line = new List[max + 1];
        for (int i = 0; i < n; i++) {
            int[] p = paint[i];
            int enter = p[0], exit = p[1];
            if (line[enter] == null)
                line[enter] = new ArrayList<>();
            if (line[exit] == null)
                line[exit] = new ArrayList<>();
            line[enter].add(new Node(i, true));
            line[exit].add(new Node(i, false));
        }
        int[] res = new int[n];
        // 记录paint数组里面的index
        TreeSet<Integer> treeset = new TreeSet<>();
        for (int i = 0; i <= max; i++) {
            if (line[i] != null) {
                for (Node node : line[i]) {
                    if (node.isEnter) {
                        treeset.add(node.index);
                    } else {
                        treeset.remove(node.index);
                    }
                }
            }
            if (!treeset.isEmpty()) {
                res[treeset.first()]++;
            }
        }
        return res;
    }
}