package com.leetcode.string;

public class _157_ReadNCharactersGivenRead4 {
    /**
     * 157. Read N Characters Given Read4
     * When:2019/8/6
     * Difficulty: Easy
     * @param buf
     * @param n
     * @return
     */
    // 题目意思不太理解
    // time:O(n) space:O(1)
    public int read(char[] buf, int n) {
        char[] temp = new char[4];
        int index = 0;
        while (true) {
            int count = read4(temp);
            count = Math.min(count, n - index); // 需要继续加入到buff的长度
            for (int i = 0; i < count; i++) {
                buf[index++] = temp[i];
            }
            if (index == n || count < 4) return index;
        }
    }

    // 辅助函数，内容不是这样写的！！
    public int read4(char[] temp) {
        return 1;
    }
}