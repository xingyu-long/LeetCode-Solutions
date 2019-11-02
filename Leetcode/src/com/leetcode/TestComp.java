package com.leetcode;

import java.util.PriorityQueue;

public class TestComp {

    public static class Pair implements Comparable<Pair>{
        int studentNo;
        int years;
        int money;

        public Pair(int No, int year, int money) {
            this.studentNo = No;
            this.years = year;
            this.money = money;
        }
        @Override
        public int compareTo(Pair pair) {
            if (this.studentNo != pair.studentNo) return this.studentNo - pair.studentNo;
            else if (this.years != pair.years) return this.years - pair.years;
            else return this.money - pair.money;
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(3,2019, 1000));
        pq.add(new Pair(3,2018, 1000));
        pq.add(new Pair(3,2019, 1000));
        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            System.out.println("No = " + pair.studentNo + "year = " + pair.years + "money = " + pair.money);
        }
    }
}
