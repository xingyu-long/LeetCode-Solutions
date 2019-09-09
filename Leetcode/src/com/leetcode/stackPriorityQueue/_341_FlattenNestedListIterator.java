package com.leetcode.stackPriorityQueue;

import com.leetcode.common.NestedInteger;

import java.util.List;
import java.util.Stack;

public class _341_FlattenNestedListIterator {

    /**
     * 341. Flatten Nested List Iterator
     * time: 2019/9/5
     * Difficulty: Medium
     */
    // time:O(n) space:O(n)
    Stack<NestedInteger> stack;
    public _341_FlattenNestedListIterator(List<NestedInteger> nestedList) {
        // 导入stack这样可以倒着输出
        stack = new Stack<>();
        for (int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    public Integer next() {
        return stack.pop().getInteger();
    }

    public boolean hasNext() {
        while (!stack.isEmpty()) {
            NestedInteger cur = stack.peek();
            if (cur.isInteger()) {
                return true;
            }
            stack.pop();
            // 当前是list，所以需要反着导入stack
            for (int i = cur.getList().size() - 1; i >= 0; i--) {
                stack.push(cur.getList().get(i));
            }
        }
        return false;
    }
}
