package com.leetcode;

import java.util.ArrayList;
import java.util.List;

public class _68_TextJustification {

    /**
     * 68. Text Justification
     * When: 2019/04/02
     *
     * Input:
     * words = ["This", "is", "an", "example", "of", "text", "justification."]
     * maxWidth = 16
     * Output:
     * [
     *    "This    is    an",
     *    "example  of text",
     *    "justification.  "
     * ]
     * @param words
     * @param maxWidth
     * @return
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int index = 0; // 用来循环其words里面的个数
        while (index < words.length) {
            int count = words[index].length(); // 当前单词的长度
            int last = index + 1; // 记录下一个单词
            while (last < words.length) {
                // 如果后面的一个导致了超过maxWidth 所以就得换行， 不然就count 加上并且中间至少放一个空格
                if (words[last].length() + count + 1 > maxWidth) break;
                count += 1 + words[last].length();
                last++;
            }
            StringBuilder sb = new StringBuilder(); // 用来存放每一行的结果
            sb.append(words[index]); //加入第一个单词
            int diff = last - index - 1; // 用来计算单词之间的空格数
            // 最后一种情况，即只有一个单词然后需要填满左边 后面留空
            if (last == words.length || diff == 0) {
                for (int i = index + 1; i < last; i++) {
                    sb.append(" ");
                    sb.append(words[i]);
                }
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else {
                int spaces = (maxWidth - count) / diff; // 记录左右分布
                int r = (maxWidth - count) % diff; // 记录左右分布的差值
                for (int i = index + 1; i < last; i++) {
                    for (int k = spaces; k > 0; k--) {
                        sb.append(" ");
                    }
                    if (r > 0) {
                        sb.append(" ");
                        r--;
                    }
                    sb.append(" ");
                    sb.append(words[i]);
                }
            }
            res.add(sb.toString());
            index = last;
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        System.out.println(fullJustify(words, maxWidth));
    }
}
