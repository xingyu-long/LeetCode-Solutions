/*
 * @Date: 03/17/2021 10:11:46
 * @LastEditTime: 03/17/2021 10:12:13
 * @Description: Random, Circle
 */
package com.leetcode.random;

public class _478_GenerateRandomPointInACircle {
    public double radius, x_center, y_center;

    public _478_GenerateRandomPointInACircle(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
    }

    public double[] randPoint() {
        double len = Math.sqrt(Math.random()) * radius;
        double deg = Math.random() * 2 * Math.PI;
        double x = x_center + len * Math.cos(deg);
        double y = y_center + len * Math.sin(deg);
        return new double[] { x, y };
    }
}