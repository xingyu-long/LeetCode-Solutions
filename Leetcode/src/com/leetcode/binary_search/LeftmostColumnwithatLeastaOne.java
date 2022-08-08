package com.leetcode.binary_search;

/**
 * @Date: 04/21/2020
 * @Description: Binary Search
 **/
public class LeftmostColumnwithatLeastaOne {
    // 简单的方法则是从右上角开始遍历
    /*
    def leftMostColumnWithOne(self, binaryMatrix: 'BinaryMatrix') -> int:
        m, n = binaryMatrix.dimensions()
        x = 0
        y = n - 1
        res = -1
        while x < m and y >= 0:
            if (binaryMatrix.get(x, y) == 1):
                res = y
                y = y - 1
            else:
                x = x + 1
        return res
    * */

}
