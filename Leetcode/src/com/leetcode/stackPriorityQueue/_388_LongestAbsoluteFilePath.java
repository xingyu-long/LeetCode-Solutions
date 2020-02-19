package com.leetcode.stackPriorityQueue;

import java.util.HashMap;
import java.util.Stack;

public class _388_LongestAbsoluteFilePath {

    /**
     * 388. Longest Absolute File Path
     * When:2019/7/12
     * review1:2019/9/3
     * Difficulty: Medium
     *
     * @param input
     * @return
     */

    //time: O(n) space:O(n)
    public static int lengthLongestPath(String input) {
        Stack<Integer> stack = new Stack<>();
        String[] arr = input.split("\n");
        int res = 0;
        stack.push(0);
        for (String s : arr) {
            // 每一层的有多少tab？
            // level = tab + 1
            int numOfTabs = s.lastIndexOf("\t") + 1;
            int level = numOfTabs + 1;
            System.out.print("s = " + s + " ");
            // 删除同等level的上的其他值
            while (level < stack.size()) {
                stack.pop();
            }
            System.out.print("s.len = " + s.length() + " ");
            int len = stack.peek() + s.length() - numOfTabs + 1; // 相当于把 `\t\t...` 变为 `\`
            System.out.println("len = " + len);
            stack.push(len);
            if (s.contains(".")) {
                // 已经包含了.txt文件
                // 减去根目录一开始保存的"加1操作"
                res = Math.max(res, len - 1);
            }
        }
        return res;
    }

    // 利用hashMap 然后当做字符串逐渐读取这样会很好理解
    public int lengthLongestPath2(String input) {
        int res = 0, count = 0, level = 1;
        boolean isFile = false;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int i = 0;
        int n = input.length();
        while (i < n) {
            while (input.charAt(i) == '\t') {
                level++;
                i++;
            }
            // 每一次换行之前
            while (i < n && input.charAt(i) != '\n') {
                if (input.charAt(i) == '.')
                    isFile = true;

                count++;
                i++;
            }

            if (isFile) {
                res = Math.max(res, map.getOrDefault(level - 1, 0) + count);
            } else {
                map.put(level, map.getOrDefault(level - 1, 0) + count + 1);
            }

            count = 0;
            level = 1;
            isFile = false;
            i++;
        }
        return res;
    }

    public static void main(String[] args) {
        String input = "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext";
        lengthLongestPath(input);
        String test = "\t";
        System.out.println(test.length());
    }
}