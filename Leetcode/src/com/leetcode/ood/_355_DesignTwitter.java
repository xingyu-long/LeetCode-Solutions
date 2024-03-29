package com.leetcode.ood;

import java.util.*;

public class _355_DesignTwitter {

    /**
     * 355. Design Twitter
     * When:2019/7/6

        这种设计很有意思！！
     */

    private int timeStamp = 0;
    private HashMap<Integer, User> userMap; // 其userID对应其User对象。
    private final int MAX_COUNT = 10;

    class Tweet {
        public int id;
        public int time;
        public Tweet next;//每个用户的发布就用链表连接起来

        public Tweet(int id) {
            this.id = id;
            time = timeStamp++;
            next = null;
        }
    }

    class User {
        public int id;
        public HashSet<Integer> followed;
        public Tweet tweetHead;

        public User(int id) {
            this.id = id;
            followed = new HashSet<>();
            follow(id);
            tweetHead = null;
        }

        public void follow(int id) {
            followed.add(id);
        }

        public void unFollow(int id) {
            followed.remove(id);
        }

        // 每次发布一条twitter，就把放到链表的起始位置
        public void post(int id) {
            Tweet tweet = new Tweet(id);
            tweet.next = tweetHead;
            tweetHead = tweet;
        }
    }

    public _355_DesignTwitter() {
        userMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            User user = new User(userId);
            userMap.put(userId, user);
        }
        userMap.get(userId).post(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new LinkedList<>();

        if (!userMap.containsKey(userId)) return res;
        //获取所有关注的列表
        HashSet<Integer> users = userMap.get(userId).followed;
        //用来逆序输出关注列表的post内容
        PriorityQueue<Tweet> pq = new PriorityQueue<>(users.size(), (a, b) -> (b.time - a.time));

        for (int user : users) {
            Tweet tweet = userMap.get(user).tweetHead;
            if (tweet != null) {
                pq.offer(tweet);
            }
        }
        int count = 0;
        // 这里有一个问题，则就是同一个人post多个，这样消息不会被添加到后面了？ （不会，因为加入pq的时候会有序！！）
        while (!pq.isEmpty() && count < MAX_COUNT) {
            Tweet tweet = pq.poll();
            res.add(tweet.id);
            count++;
            if (tweet.next != null) {
                pq.offer(tweet.next);
            }
        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        // 先判断用户是否存在，不存在就新建其对象，存在的话就直接follow操作
        if (!userMap.containsKey(followerId)) {
            User user = new User(followerId);
            userMap.put(followerId, user);
        }
        if (!userMap.containsKey(followeeId)) {
            User user = new User(followeeId);
            userMap.put(followeeId, user);
        }
        userMap.get(followerId).follow(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId) || followerId == followeeId) {
            return;
        }
        userMap.get(followerId).unFollow(followeeId);
    }
}
