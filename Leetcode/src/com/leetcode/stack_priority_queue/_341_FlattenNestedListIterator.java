package com.leetcode.stack_priority_queue;

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
        while (!stack.isEmpty()) { // 这样可以确保，我们能够得到integer在里面
            NestedInteger cur = stack.peek();
            if (cur.isInteger()) {
                return true;
            }
            stack.pop();
            // 当前是list，所以需要反着导入stack
            List<NestedInteger> next = curr.getList();
            for (int i = next.size() - 1; i >= 0; i--) {
                stack.push(next.get(i));
            }
        }
        return false;
    }

    // 也可以利用dfs先遍历完。
    public List<Integer> res;

    public void dfs(List<NestedInteger> nestedList) {
        for (NestedInteger i : nestedList) {
            if (i.isInteger()) res.add(i.getInteger());
            else dfs(i.getList());
        }
    }
}
