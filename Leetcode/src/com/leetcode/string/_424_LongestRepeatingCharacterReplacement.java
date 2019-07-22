package com.leetcode.string;

public class _424_LongestRepeatingCharacterReplacement {

    /**
     *  424. Longest Repeating Character Replacement
     *  When:2019/7/22
     *  Difficulty: Medium
     *
     *  不需要内部更新max 理由：因为这个size只会增大才会更新res，这个max指全局的多数元素的个数
     *  看下面的评论
     *  https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91285/Sliding-window-similar-to-finding-longest-substring-with-k-distinct-characters
     *
     * @param s
     * @param k
     * @return
     */
    // time: O(n) space:O(1) 因为只是固定的为26大小的int数组
    public int characterReplacement(String s, int k) {
        //需要解决的问题：1.如何判断替换的位置？ 通过维护sliding window 并且比较内部含有的不同的字母的个数
        int[] count = new int[26];
        int start = 0;
        int res = 0;
        int max = 0;//当前占据多数的字符的个数
        for (int i = 0; i < s.length(); i++) {
            max = Math.max(max, ++count[s.charAt(i) - 'A']);
            while (i - start + 1 - max > k) {// 看是否符合一个size里面含有小于k的不同字符
                // 内部不需要更新max具体理由看上面链接
                count[s.charAt(start) - 'A']--;// 移动start，所以要把那个计数删除
                start++;
            }
            res = Math.max(res, i - start + 1);
        }
        return res;
    }
}
