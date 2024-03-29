/*
 * @Date: 09/13/2021 18:45:14
 * @LastEditTime: 09/14/2021 19:09:09
 * @Description: Counting
 */
public class _1189_MaximumNumberOfBalloons {
    public int maxNumberOfBalloons(String text) {
        int[] count = new int[26];
        int[] targetCount = new int[26];
        for (char ch : text.toCharArray()) {
            count[ch - 'a']++;
        }
        String target = "balloon";
        for (char ch : target.toCharArray()) {
            targetCount[ch - 'a']++;
        }

        int res = Integer.MAX_VALUE;
        for (char ch : target.toCharArray()) {
            res = Math.min(res, count[ch - 'a'] / targetCount[ch - 'a']);
        }
        return res;
    }
}
