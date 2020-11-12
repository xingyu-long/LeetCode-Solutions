package com.new_grad.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class amazon_debt_records {
    // HashMap;
    public static class debtRecord {
        String borrower = "";
        String lender = "";
        int amount = 0;

        debtRecord() {
            // empty constructor
        }

        debtRecord(String borrower, String lender, int amount) {
            this.borrower = borrower;
            this.lender = lender;
            this.amount = amount;
        }
    }

    public static List<String> minimumDebtMembers(List<debtRecord> records) {
        Map<String, Integer> personToDebt = new HashMap<>();
        for (debtRecord record : records) {
            personToDebt.putIfAbsent(record.borrower, 0);
            personToDebt.putIfAbsent(record.lender, 0);
            personToDebt.put(record.borrower, personToDebt.get(record.borrower) - record.amount);
            personToDebt.put(record.lender, personToDebt.get(record.lender) + record.amount);
        }

        int min = Integer.MAX_VALUE;
        for (int money : personToDebt.values()) {
            min = Math.min(min, money);
        }
        List<String> res = new ArrayList<>();
        if (min > 0) return res;
        for (String person : personToDebt.keySet()) {
            if (personToDebt.get(person) == min) {
                res.add(person);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        List<debtRecord> list = new ArrayList<>();
        list.add(new debtRecord("Alex", "Blake", 2));
        list.add(new debtRecord("Blake", "Alex", 2));
        list.add(new debtRecord("Casey", "Alex", 5));
        list.add(new debtRecord("Blake", "Casey", 7));
        list.add(new debtRecord("Alex", "Blake", 4));
        list.add(new debtRecord("Alex", "Casey", 4));
        List<String> res = minimumDebtMembers(list);
        System.out.println(res.toString());
    }
}
