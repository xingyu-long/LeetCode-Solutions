package com.leetcode.string;

/**
 * @Date: 06/16/2020
 * @Description: IP, String
 **/
public class _468_ValidateIPAddress {
    // time:O(n) space:O(1)
    public String validIPAddress(String IP) {
        if (isIPv4(IP)) {
            return "IPv4";
        } else if (isIPv6(IP.toUpperCase())) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean isIPv4(String ip) {
        int count = 0;
        for (char ch : ip.toCharArray()) {
            if (ch == '.') {
                count++;
            }
        }
        if (count != 3) {
            return false;
        }
        String[] fields = ip.split("\\.");
        if (fields.length != 4) {
            return false;
        }
        for (String field : fields) {
            if (field.isEmpty() || field.length() > 3) {
                return false;
            }
            for (int i = 0; i < field.length(); i++) {
                if (!Character.isDigit(field.charAt(i))) {
                    return false;
                }
            }
            int num = Integer.parseInt(field);
            // 这种情况就是为了防止 01.01.01.01
            if (!String.valueOf(num).equals(field) || num < 0 || num > 255) {
                return false;
            }
        }
        return true;
    }


    private boolean isIPv6(String ip) {
        int count = 0;
        for (char ch : ip.toCharArray()) {
            if (ch == ':') {
                count++;
            }
        }
        if (count != 7) {
            return false;
        }
        String[] fields = ip.split(":");
        if (fields.length != 8) {
            return false;
        }
        for (String field : fields) {
            if (field.isEmpty() || field.length() > 4) {
                return false;
            }
            for (int i = 0; i < field.length(); i++) {
                if (!Character.isDigit(field.charAt(i)) && (field.charAt(i) < 'A'
                    || field.charAt(i) > 'F')) {
                    return false;
                }
            }
        }
        return true;
    }
}
