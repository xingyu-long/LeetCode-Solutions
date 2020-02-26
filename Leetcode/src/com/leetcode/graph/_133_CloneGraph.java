package com.leetcode.graph;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _133_CloneGraph {

    private class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }
    };
    private HashMap<Node, Node> map = new HashMap<>();

    /**
     *  133. Clone Graph
        When: 2019/06/30, 02/25/2020

        solution:
        DFS
     * @param node
     * @return
     */
    //time: O(n) space: O(n)
    public Node cloneGraph(Node node) {
        return clone(node);
    }

    private Node clone(Node node) {
        if (node == null) return null;

        if (! map.containsKey(node)) {
            Node newNode = new Node(node.val);
            map.put(node, newNode);
            List<Node> neighbors = new LinkedList<>();
            for (int i = 0; i < node.neighbors.size(); i++) {
                neighbors.add(clone(node.neighbors.get(i)));
            }
            newNode.neighbors = neighbors;
        }
        return map.get(node);
    }


    // BFS, 利用HashMap并且可以做标记。不会添加重复的node。
    public Node cloneGraph2(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node, null);
        // copy the node into the hashmap.
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            map.put(cur, new Node(cur.val));
            for (Node adj : cur.neighbors) {
                if (!map.containsKey(adj)) { // 刚好也可以作为标记的作用
                    queue.offer(adj);
                    map.put(adj, null);
                }
            }
        }

        // construct the neighbor relationships.
        for (Node originalV : map.keySet()) {
            Node copy = map.get(originalV);
            copy.neighbors = new LinkedList<>();
            for (Node adj : originalV.neighbors) {
                copy.neighbors.add(map.get(adj)); // 这里是map.get(adj)
            }
        }
        return map.get(node);
    }

    // BFS写在一起
    public Node cloneGraph3(Node node) {
        if (node == null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        map.put(node, new Node(node.val));
        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            for (Node neig : curr.neighbors) {
                if (!map.containsKey(neig)) {
                    queue.offer(neig);
                    map.put(neig, new Node(neig.val));
                }
                // 这里稍微注意下 应该都是选对应的情况
                map.get(curr).neighbors.add(map.get(neig));
            }
        }
        return map.get(node);
    }
}
