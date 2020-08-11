package com.leetcode.stackPriorityQueue;

import java.util.Stack;

public class _71_SimplifyPath {

    /**
     * 71. Simplify Path
     * When: 2019/06/08
     * Review1: 2019/7/12
     * review2: 2019/9/3
     * Difficulty: Medium
     * <p>
     * solution: 使用stack来保存其字符 遇到.. 就返回上一层 遇到 . 和空白就不管；遇到字符就push
     * 最后拼接在一起
     *
     * @param path
     * @return
     */
    // time: O(n) space: O(n) (due to the stack structure)
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        String[] paths = path.split("/+");
        for (String s : paths) {
            if (s.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!s.equals(".") && !s.equals("")) {
                stack.push(s);
            }
        }
        String res = "";
        // 这里用的比较妙，先出来的其实在路径的后方。
        while (!stack.isEmpty()) {
            res = "/" + stack.pop() + res;
        }
        if (res.length() == 0) {
            return "/";
        }
        return res;
    }
    // 也可以用deque来模拟stack，输出的时候感觉会方便些
}
