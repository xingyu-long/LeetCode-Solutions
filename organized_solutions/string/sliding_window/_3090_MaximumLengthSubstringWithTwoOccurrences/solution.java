class Solution {
    public int maximumLengthSubstring(String s) {
        int n = s.length();
        // sliding window
        int res = 0;
        int start = 0, end = 0;
        char[] count = new char[256];
        while (end < n) {
            char ch = s.charAt(end);
            count[ch]++;
            while (count[ch] > 2) {
                count[s.charAt(start)]--;
                start++;
            }
            // System.out.printf("substring=%s\n", s.substring(start, end+1));
            res = Math.max(res, end - start + 1);
            end++;
        }
        return res;
    }
}