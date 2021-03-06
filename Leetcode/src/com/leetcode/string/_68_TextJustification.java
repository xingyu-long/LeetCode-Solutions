package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _68_TextJustification {

    /**
     * 68. Text Justification
     * When: 2019/04/02
     * review1:2019/8/8
     *
     * Input:
     * words = ["This", "is", "an", "example", "of", "text", "justification."]
     * maxWidth = 16
     * Output:
     * [
     * "This    is    an",
     * "example  of text",
     * "justification.  "
     * ]
     *
     * @param words
     * @param maxWidth
     * @return
     */
    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int index = 0; // 用来循环其words里面的个数并且代表每一行的开始的字符串数组坐标
        while (index < words.length) {
            int count = words[index].length(); // 当前单词的长度
            int last = index + 1; // 记录这一行能够存到那个单词的后一个（因为count加了之后才验证是否大于）
            while (last < words.length) {
                // 如果后面的一个导致了超过maxWidth 所以就得换行， 不然就count 加上并且中间至少放一个空格
                if (words[last].length() + count + 1 > maxWidth) break;
                count += 1 + words[last].length();
                last++;
            }
            StringBuilder sb = new StringBuilder(); // 用来存放每一行的结果
            sb.append(words[index]); //加入第一个单词
            int diff = last - index - 1; // 用来计算单词之间的空格数
            // 最后一种情况，属于最后一行，就算有多个单词也是向左对其！
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
                    if (r > 0) { // 这里每次的可能左边与右边多的部分！！，那个science的example需要注意
                        sb.append(" "); //
                        r--;
                    }
                    sb.append(" "); // 中间的那个，原来count里面计算好的至少一个空格
                    sb.append(words[i]);
                }
            }
            res.add(sb.toString());
            index = last;
        }
        return res;
    }

    public static List<String> fullJustify2(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int index = 0;
        while (index < words.length) {
            int count = words[index].length();
            int last = index + 1;
            while (last < words.length && words[last].length() + count + 1 <= maxWidth) {
                count += words[last].length() + 1;
                last++;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(words[index]);
            int diff = last - index - 1;
            if (last == words.length || diff == 0) { // 表示最后一行
                for (int i = index + 1; i < last; i++) {
                    sb.append(" ");
                    sb.append(words[i]);
                }
                for (int i = sb.length(); i < maxWidth; i++) {
                    sb.append(" ");
                }
            } else {
                int spaces = (maxWidth - count) / diff;
                int remain = (maxWidth - count) % diff;
                for (int i = index + 1; i < last; i++) {
                    // print the left
                    for (int j = 0; j < spaces; j++) {
                        sb.append(" ");
                    }
                    if (remain > 0) {
                        remain--;
                        sb.append(" ");
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
        String[] words = new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain", "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        int maxWidth = 20;
        System.out.println(fullJustify2(words, maxWidth));
    }
}
