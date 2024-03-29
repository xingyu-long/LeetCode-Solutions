package com.leetcode.ood;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;

public class _353_DesignSnakeGame {
    // 主要处理每次的头和尾巴的部分
    HashSet<Integer> set; // 位置
    Deque<Integer> deque; // 保持head的位置
    int score;
    int foodIndex;
    int width;
    int height;
    int[][] food;

    public _353_DesignSnakeGame(int width, int height, int[][] food) {
        this.width = width;
        this.height = height;
        this.food = food;
        set = new HashSet<>();
        deque = new LinkedList<>();
        score = 0;
        foodIndex = 0;
        set.add(0);
        deque.offerLast(0);
    }

    public int move(String direction) {
        if (score == -1) {
            return -1;
        }

        int rowHead = deque.peekFirst() / width;
        int colHead = deque.peekFirst() % width;

        switch (direction) {
            case "U" : rowHead--;
                break;
            case "D" : rowHead++;
                break;
            case "L" : colHead--;
                break;
            default : colHead++;
        }
        int head = rowHead * width + colHead;
        // 先删除尾巴
        set.remove(deque.peekLast());
        // 看新的头是否越界或者是碰上了自己
        if (rowHead < 0 || rowHead == height || colHead < 0 || colHead == width || set.contains(head)) {
            return score = -1;
        }
        // 添加新头
        set.add(head);
        deque.offerFirst(head);

        if (foodIndex < food.length && rowHead == food[foodIndex][0] && colHead == food[foodIndex][1]) {
            foodIndex++;
            ++score;
            // 尾巴还是加入到set里面（set用来判断是否会撞到自己，然后吃掉）
            set.add(deque.peekLast());
            return score;
        } else { // 单纯的移动，已经加入了移动后的head，需要把尾巴删除。
            deque.pollLast();
        }
        return score;
    }

    public static void main(String[] args) {
        int width = 3;
        int height = 2;
        int[][] food = {{1,2}, {0, 1}};
        _353_DesignSnakeGame snake = new _353_DesignSnakeGame(width, height, food);
        System.out.println(snake.move("R"));
        System.out.println(snake.move("D"));
        System.out.println(snake.move("R"));
        System.out.println(snake.move("U"));
        System.out.println(snake.move("L"));
        System.out.println(snake.move("U"));
    }
}
