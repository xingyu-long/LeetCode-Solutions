package com.leetcode.stackPriorityQueue;

import java.util.Stack;

/**
 * @Date: 2019/06/08, 2019/7/12, 2019/9/3, 05/20/2020
 * @Description: Sliding Window
 **/
public class _71_SimplifyPath {
    // 关注..会需要返回上一层即可以及后面构建的时候注意顺序
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
