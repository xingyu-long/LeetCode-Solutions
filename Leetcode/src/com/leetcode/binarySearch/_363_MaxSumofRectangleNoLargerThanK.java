import java.util.TreeSet;

/*
 * @Date: 07/05/2022 21:54:57
 * @LastEditTime: 07/05/2022 22:05:15
 * @Description: You need to fill out
 */

 public class _363_MaxSumofRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix.length == 0) return 0;
        // brute force:
        // time: O(m * n * m * n * m * n)
        
        // time: O(n * n * m + n * n * m * logM)
        int m = matrix.length, n = matrix[0].length;
        int res = Integer.MIN_VALUE;
        for (int colStart = 0; colStart < n; colStart++) {
            // 尝试从colStart到colEnd可以压扁成的和
            int[] sums = new int[m];
            for (int colEnd = colStart; colEnd < n; colEnd++) {
                for (int i = 0; i < m; i++) {
                    sums[i] += matrix[i][colEnd];
                }
                // 找到 prefix[j] - prefix[i] <= k的最大的情况
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                int prefix = 0;
                for (int sum : sums) {
                    prefix += sum;
                    // 找到num >= prefix - k;
                    Integer num = set.ceiling(prefix - k);
                    if (num != null) {
                        // prefix - num <= k
                        res = Math.max(res, prefix - num);
                    }
                    set.add(prefix);
                }
            }
        }
        return res;
    }
 }