package com.leetcode.dynamic_programming;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class _1359_CountAllValidPickupandDeliveryOptions {
    // When:2/22/2020
    // 全排列的分析，通过数学推理。
    // 假设前n-1对已经安置好了，目前放置最后一对。 2 * 之前的 n-1 + 1  -> 2 * n - 1那放置最后一个的可能就是Delivery则就是 2 * n.
    // 然后由于需要pick up在delivery前面所以结果除以2
    // time:O(n)
    // space:O(1)
    public int countOrders(int n) {
        long res = 1;
        int mod = (int) Math.pow(10, 9) + 7;
        for (int i = 1; i <= n; i++) {
            res = res * (2 * i - 1) * i % mod;
        }
        return (int) res;
    }

    public int daysBetweenDates(String date1, String date2) {
//        if (date1.compareTo(date2) < 0) {
//            String temp = date1;
//            date1 = date2;
//            date2 = temp;
//        }
        SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");//如2016-08-10 20:40
        Date from = null;
        Date to = null;
        try {
            from = simpleFormat.parse(date1);
            to = simpleFormat.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long num1 = from.getTime();
        long num2 = to.getTime();

        int res = (int) (num1 - num2) / (1000 * 60 * 60 * 24);
        return res;
        // String from = s
    }

    class Num implements Comparable<Num>{
        int n1;
        int n2;

        public Num(int n1, int n2) {
            this.n1 = n1;
            this.n2 = n2;
        }

        public int compareTo(Num o) {
            return Math.abs(this.n1 - this.n2) - Math.abs(o.n1 - o.n2);
        }
    }
    public int[] closestDivisors(int num) {
        int[] res = new int[2];
        List<Num> list = new ArrayList();
        for (int i = 1; i <= num / 2; i++) {
            if ((num + 1) % i == 0) {
                list.add(new Num(i, (num + 1)/ i));
            }
            if ((num + 2) % i == 0) {
                list.add(new Num(i, (num + 2)/ i));
            }
        }
        Collections.sort(list);
        res[0] = list.get(0).n1;
        res[1] = list.get(0).n2;
        return res;
    }


    public static void main(String[] args) {
        _1359_CountAllValidPickupandDeliveryOptions test = new _1359_CountAllValidPickupandDeliveryOptions();
        String date2 = "2010-09-23";
        String date1 = "2009-06-29";
    }
}
