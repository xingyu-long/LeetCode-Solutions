package com.leetcode.string;

/**
 * @Date: 05/07/2020
 * @Description: Two Pointer
 **/
public class _953_VerifyinganAlienDictionary {

    // time:(words) * isValid. space:O(1);
    public boolean isAlienSorted(String[] words, String order) {
        // 一一比较吧 不用担心第三个比第一个小的问题，因为前面一旦判断过了就应该是大于的。
        if (words == null || words.length == 0) {
            return true;
        }
        for (int i = 1; i < words.length; i++) {
            if (!isValid(words[i - 1], words[i], order)) {
                return false;
            }
        }
        return true;
    }

    // time:min(s,t) * O(order)
    public boolean isValid(String s, String t, String order) {
        int i = 0, j = 0;
        while (i < s.length() && j < t.length()) {
            int indexS = order.indexOf(s.charAt(i));
            int indexT = order.indexOf(t.charAt(j));
            if (indexS < indexT) {
                return true;
            } else if (indexS > indexT) {
                return false;
            } else {
                i++;
                j++;
            }
        }
        // 表明前面都匹配上了，例如app和appl
        if (s.length() <= t.length()) {
            return true;
        }
        return false;
    }

    // 先map到该有的index，其实和上面的没啥区别。。。就是把indexOf去掉了
    // time:O(words * max(word))
    public boolean isAlienSorted2(String[] words, String order) {
        if (words == null || words.length == 0) {
            return true;
        }
        int[] mapping = new int[26];
        for (int i = 0; i < 26; i++) {
            mapping[order.charAt(i) - 'a'] = i;
        }
        for (int i = 1; i < words.length; i++) {
            if (!isValid(words[i - 1], words[i], mapping)) {
                return false;
            }
        }
        return true;
    }

    public boolean isValid(String s, String t, int[] mapping) {
        int m = s.length(), n = t.length();
        for (int i = 0; i < m && i < n; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                int idxS = mapping[s.charAt(i) - 'a'];
                int idxT = mapping[t.charAt(i) - 'a'];
                if (idxS > idxT) {
                    return false;
                }
                if (idxS < idxT) {
                    return true;
                }
            }
        }
        if (m <= n) {
            return true;
        }
        return false;
    }
}
