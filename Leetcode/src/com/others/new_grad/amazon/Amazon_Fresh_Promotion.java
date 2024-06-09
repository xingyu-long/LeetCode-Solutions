package com.new_grad.amazon;

public class Amazon_Fresh_Promotion {
    // 就像是字符串匹配问题。但是有没有时间复杂度要求？
    public static int winPrize(String[][] codeList, String[] shoppingCart) {
        if (codeList == null || codeList.length == 0) {
            return 1;
        }
        if (shoppingCart == null || shoppingCart.length == 0) {
            return 0;
        }

        int i = 0, j = 0;
        // 因为题目说了order matters
        while (i < codeList.length && j + codeList[i].length <= shoppingCart.length) {
            boolean match = true;
            for (int k = 0; k < codeList[i].length; k++) {
                if (!codeList[i][k].equals("anything") && !shoppingCart[j + k].equals(codeList[i][k])) {
                    match = false;
                    break;
                }
            }
            if (match) {
                j += codeList[i].length;
                i++;
            } else {
                j++;
            }
        }
        return i == codeList.length ? 1 : 0;
    }
}
