/*
 * @Date: 08/08/2022 18:48:57
 * @LastEditTime: 08/19/2022 11:09:06
 * @Description: Counting Sort, Heap
 */
package com.leetcode.string;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _767_ReorganizeString {

    // time:O(n) space:O(n + 26)
    public String reorganizeString(String S) {
        int[] counter = new int[26];
        for (int i = 0; i < S.length(); i++) {
            counter[S.charAt(i) - 'a']++;
        }
        int max = 0, letter = 0;
        for (int i = 0; i < counter.length; i++) {
            if (counter[i] > max) {
                max = counter[i];
                letter = i;
            }
        }
        if (max > (S.length() + 1) / 2) {
            return "";
        }
        char[] res = new char[S.length()];
        int index = 0;
        // 先赋值最多的letter，
        while (counter[letter] > 0) {
            res[index] = (char) (letter + 'a');
            index += 2;
            counter[letter]--;
        }

        for (int i = 0; i < counter.length; i++) {
            while (counter[i] > 0) {
                if (index >= res.length) {
                    index = 1;// 从头开始。
                }
                res[index] = (char) (i + 'a');
                index += 2;
                counter[i]--;
            }
        }
        return new String(res);
    }

    // time:O(nlogn) space:O(n)
    public String reorganizeString2(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : S.toCharArray()) {
            int count = map.getOrDefault(ch, 0) + 1;
            if (count > (S.length() + 1) / 2)
                return "";
            map.put(ch, count);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[1] - a[1])); // 希望把多的先安置下来，这里是有一个greedy的套路
        for (char ch : map.keySet()) {
            pq.offer(new int[] { ch, map.get(ch) });
        }
        // 构建字符串
        // 相当于是利用ch:int 这样的数目做贪心，但是如果遇到abb的情况，首先poll出一个b，
        // 然后a,b的话就poll其中一个，但是如果与前面的重复，则不加入
        // 直接弹出second，最后把first也放入。
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (sb.length() == 0 || first[0] != sb.charAt(sb.length() - 1)) {
                sb.append((char) first[0]);
                if (--first[1] > 0) { // 减去当前的个数之后，如果还有剩余则会用在后面
                    pq.offer(first);
                }
            } else {
                int[] second = pq.poll();
                sb.append((char) second[0]);
                if (--second[1] > 0) {
                    pq.offer(second);
                }
                pq.offer(first);
            }
        }
        return sb.toString();
    }

    public class Item {
        char ch;
        int freq;
        
        public Item(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }
    // 这个解法更加直接
    public String reorganizeString3(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> (b.freq - a.freq));
        for (char ch : map.keySet()) {
            pq.offer(new Item(ch, map.get(ch)));
        }
        StringBuilder sb = new StringBuilder();
        while (pq.size() >= 2) {
            Item item1 = pq.poll(), item2 = pq.poll();
            sb.append(item1.ch).append(item2.ch);
            if (--item1.freq > 0) pq.offer(item1);
            if (--item2.freq > 0) pq.offer(item2);
        }
        if (!pq.isEmpty()) {
            sb.append(pq.poll().ch);
        }
        return sb.toString().length() == s.length() ? sb.toString() : "";
    }
}
