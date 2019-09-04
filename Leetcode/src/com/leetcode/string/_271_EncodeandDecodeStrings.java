package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _271_EncodeandDecodeStrings {

    /**
     * 271. Encode and Decode Strings
     * When:2019/8/6
     * Difficulty: Medium
     * @param strs
     * @return
     */
    // 如果使用空格的话，原输入有空格就会有问题
    // time:O(n) space:O(n)
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            sb.append(str.length()).append('/').append(str);
        }
        return sb.toString();
    }

    public List<String> decode(String s) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < s.length()) {
            int slash = s.indexOf('/', i); //从i开始找第一个 '/'
            int size = Integer.valueOf(s.substring(i, slash));
            res.add(s.substring(slash + 1, slash + size + 1));
            i = slash + size + 1;// 下一个的开始
        }
        return res;
    }
}