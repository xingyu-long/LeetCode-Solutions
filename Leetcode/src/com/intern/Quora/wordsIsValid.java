package com.intern.Quora;

import java.util.HashSet;
import java.util.Set;

public class wordsIsValid {
    // 输入一组words和一组valid letters，判断有多少个words是valid。
    // 判断条件是words里的所有upper and lower letter必须在valid letters里面。
    // 如果word里面有special character不用管。注意valid letter只有小写，
    // 但是words里面有大写的也算valid。
    // 比如words = [hEllo##, This^^],
    // valid letter = [h, e, l, 0, t, h, s];
    // "hello##" 就是valid，因为h，e，l，o都在valid letter 里面，
    // “This^^” 不valid, 因为i不在valid letter里面
    public static int wordsIsValid(String[] words, char[] letters) {
        if (words == null || words.length == 0 ||
                letters == null || letters.length == 0) return 0;
        Set<Character> set = new HashSet<>();
        int res = 0;
        for (char ch : letters) {
            set.add(Character.toLowerCase(ch));
        }
        // 开始遍历每一个字符串
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            boolean isValid = true;
            for (int j = 0; j < word.length(); j++) {
                char ch = word.charAt(j);
                if (Character.isLetter(ch)) {
                    ch = Character.toLowerCase(ch);
                    if (!set.contains(ch)) {
                        isValid = false;
                        break;
                    }
                }
            }
            if (isValid) res++;
        }
        return res;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"hEllo##", "This^^"};
        char[] chars = {'h', 'e', 'l', 'o', 't', 'h', 's'};
        System.out.println(wordsIsValid(words, chars));
    }
}
