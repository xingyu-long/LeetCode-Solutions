/*
 * @Date: 07/30/2022 14:48:21
 * @LastEditTime: 07/30/2022 14:48:21
 * @Description: Simulation
 */
package com.leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class _1169_InvalidTransactions {
    // time: O(n^2)
    public List<String> invalidTransactions(String[] transactions) {
        if (transactions == null || transactions.length == 0) {
            return new ArrayList<>();
        }
        int n = transactions.length;
        boolean[] invalid = new boolean[n];
        String[] names = new String[n];
        int[] times = new int[n];
        int[] amounts = new int[n];
        String[] cities = new String[n];

        for (int i = 0; i < n; i++) {
            String[] split = transactions[i].split(",");
            names[i] = split[0];
            times[i] = Integer.parseInt(split[1]);
            amounts[i] = Integer.parseInt(split[2]);
            cities[i] = split[3];
        }

        for (int i = 0; i < n; i++) {
            if (amounts[i] > 1000) {
                invalid[i] = true;
            }
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(times[i] - times[j]) <= 60 && names[i].equals(names[j]) && !cities[i].equals(cities[j])) {
                    invalid[i] = true;
                    invalid[j] = true;
                }
            }
        }

        List<String> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (invalid[i]) {
                res.add(transactions[i]);
            }
        }
        return res;
    }
}
