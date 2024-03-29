package com.leetcode.string.sliding_window;

public class _424_LongestRepeatingCharacterReplacement {

    // 不需要内部更新max 理由：因为这个size只会增大才会更新res，这个max指全局的多数元素的个数
    // 看下面的评论
    // https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91285/Sliding-window-similar-to-finding-longest-substring-with-k-distinct-characters
    // time: O(n) space:O(1) 因为只是固定的为26大小的int数组
    public int characterReplacement(String s, int k) {
        //需要解决的问题：1.如何判断替换的位置？ 通过维护sliding window 并且比较内部含有的不同的字母的个数
        int[] count = new int[26];
        int start = 0;
        int res = 0;
        int max = 0;//当前占据多数的字符的个数
        for (int i = 0; i < s.length(); i++) {
            max = Math.max(max, ++count[s.charAt(i) - 'A']);
            while (i - start + 1 - max > k) {// 看是否符合一个size里面含有小于k的不同字符,不然就移动start
                // 内部不需要更新max具体理由看上面链接,主要是也不会得到一个超越前面的情况
                count[s.charAt(start) - 'A']--;// 移动start，所以要把那个计数删除
                start++;
            }
            res = Math.max(res, i - start + 1);
        }
        return res;
    }


    // time:O(n) space:O(1)  这样好理解 window的概念。
    // 上面的那个不更新maxCount是因为如果后面的没有成为主要元素，前面的结果res更大一些。
    public int characterReplacement2(String s, int k) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        int begin = 0, end = 0;
        int maxCount = 0;
        int[] counter = new int[26];
        while (end < s.length()) {
            maxCount = Math.max(maxCount, ++counter[s.charAt(end) - 'A']);
            while (end - begin + 1 - maxCount > k) {
                counter[s.charAt(begin) - 'A']--;
                begin++;
                maxCount = 0;
                // 这一段距离里面的最大元素是多少
                for (int i = 0; i < 26; i++) {
                    maxCount = Math.max(maxCount, counter[i]);
                }
            }
            res = Math.max(res, end - begin + 1);
            end++;
        }
        return res;
    }
}
