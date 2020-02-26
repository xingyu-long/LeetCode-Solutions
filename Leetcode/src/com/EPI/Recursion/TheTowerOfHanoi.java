package com.EPI.Recursion;

/**
 * TheTowerOfHanoi
 */
public class TheTowerOfHanoi {

    public static void computeHanoi(int numOfRings) {
        move(numOfRings, 0, 1, 2);
    }

    public static void move(int numOfRings, int from, int to, int using) {
        if (numOfRings > 0) {
            move(numOfRings - 1, from, using, to);
            System.out.println("Move from " + from + " to " + to);
            move(numOfRings - 1, using, to, from);
        }
    }
    
    public static void main(String[] args) {
        TheTowerOfHanoi hanoi = new TheTowerOfHanoi();
        hanoi.computeHanoi(3);
    }
}