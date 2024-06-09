package com.intern.Expedia;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Factor {

    public int findFactor (long num, int index) {
        PriorityQueue<Long> pq = new PriorityQueue<>((a, b) -> b.compareTo(a));
        for (long i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                System.out.println(i);
                System.out.println(num / i);
                pq.offer(i);
                pq.offer(num / i);
                while (pq.size() > index) {
                    pq.poll();
                }
            }
        }
        // 或者利用最大堆保持nlogK
        return pq.peek().intValue();
    }

    public static class myComparator implements Comparator<String> {

        @Override
        public int compare(String s, String t) {
            return s.length() - t.length();
        }
    }

    public static void main(String[] args) {
        Factor factor = new Factor();
        String[] strs = {"abcd", "abc", "ab", "a"};
        Arrays.sort(strs, new myComparator());
        for (int i = 0; i < strs.length; i++) {
            StringBuilder sb =  new StringBuilder(strs[i]);
            sb.setCharAt(0, (char) (strs[i].charAt(i) - 32));
            strs[i] = sb.toString();
            System.out.println(strs[i]);
        }
        System.out.println("res = " + factor.findFactor(100,2));
    }
}
