/*
 * @Date: 2020-03-30 09:56:42
 * @LastEditors: Clark long
 * @LastEditTime: 2020-03-30 12:06:34
 */
package com.leetcode.array.counter;

public class _1395_CountNumberofTeams {
    
    public int res = 0;
    public int numTeams(int[] rating) {
        // search ?
        if (rating == null || rating.length == 0) return 0;
        for (int i = 0; i < rating.length - 2; i++) {
            dfs(rating, i, 1, true);
            dfs(rating, i, 1, false);
        }
        return res;
    }
        
    // 这个其实有点暴力解法 Exponential
    public void dfs(int[] rating, int index, int count, boolean increase) {
        if (count == 3)  {
            res++;
            return;
        }
        for (int i = index + 1; i < rating.length; i++) {
            if (rating[i] > rating[index] && increase) {
                dfs(rating, i, count + 1, true);
            }
            if (rating[i] < rating[index] && !increase) {
                // 直接设置为true false更直观一些
                dfs(rating, i, count + 1, false);
            }
        }
    }

    // 注意相加的位置，分别计算当前位置的前面比较情况。并且也分为增序和降序的情况，所以是四个变量
    // time:O(n^2)
    public int numTeams2(int[] rating) {
        if (rating  == null || rating.length == 0) return 0;
        int n = rating.length;
        int res = 0;
        // 以i为参考进行遍历
        for (int i = 0; i < n; i++) {
            int increaseBefore = 0, increaseAfter = 0;
            int decreaseBefore = 0, decreaseAfter = 0;
            for (int j = 0; j < n; j++) {
                if (rating[i] < rating[j]) {
                    if (i < j) {
                        increaseAfter++;
                    } else if (i > j) {
                        decreaseBefore++; // 降序里左边比自己大的情况
                    }
                }
                
                if (rating[i] > rating[j]) {
                    if (i > j) {
                        increaseBefore++;
                    } else if (j > i) {
                        decreaseAfter++;
                    }
                }
            }
             res += increaseBefore * increaseAfter + decreaseBefore * decreaseAfter;
        }
        return res;
    }
}