package com.new_grad.amazon;

import java.util.*;

public class itemAssociation {
    // https://leetcode.com/discuss/interview-question/782606/
    // "static void main" must be defined in a public class.
    static class PairString {
        String first;
        String second;

        public PairString(String first, String second) {
            this.first = first;
            this.second = second;
        }
    }


    public static void main(String[] args) {
        List<PairString> list = new ArrayList<>();
        list.add(new PairString("item1", "item2"));
        list.add(new PairString("item3", "item4"));
        list.add(new PairString("item4", "item5"));
        // item3 item4 item5
        System.out.println(largestItemAssociation(list).toString());
        System.out.println();

        list = new ArrayList<>();
        list.add(new PairString("z", "b"));
        list.add(new PairString("z", "c"));
        list.add(new PairString("c", "d"));
        list.add(new PairString("e", "f"));
        // b c z d
        System.out.println(largestItemAssociation(list).toString());
        System.out.println();

        list = new ArrayList<>();
        list.add(new PairString("y", "x"));
        list.add(new PairString("a", "y"));
        list.add(new PairString("d", "e"));
        list.add(new PairString("e", "f"));
        // a x y
        System.out.println(largestItemAssociation(list).toString());
        System.out.println();

        list = new ArrayList<>();
        list.add(new PairString("a", "b"));
        list.add(new PairString("b", "a"));
        list.add(new PairString("c", "e"));
        list.add(new PairString("e", "f"));
        list.add(new PairString("f", "c"));
        // c e f
        System.out.println(largestItemAssociation(list).toString());
        System.out.println();

        list = new ArrayList<>();
        list.add(new PairString("a", "b"));
        list.add(new PairString("b", "a"));
        list.add(new PairString("a", "c"));
        list.add(new PairString("d", "e"));
        list.add(new PairString("e", "f"));
        list.add(new PairString("f", "e"));
        list.add(new PairString("b", "e"));

        list.add(new PairString("w", "x"));
        list.add(new PairString("x", "y"));
        list.add(new PairString("y", "z"));
        // a b c d e f
        System.out.println(largestItemAssociation(list).toString());
        System.out.println();
    }

    public static List<String> largestItemAssociation(List<PairString> itemAssociation) {
        if (itemAssociation.size() == 0) return new ArrayList<>();

        UnionFind uf = new UnionFind();
        for (PairString p : itemAssociation) {
            uf.union(p.first, p.second);
        }

        // print map
        for (Map.Entry<String, Node> entry : uf.map.entrySet()) {
            System.out.print(entry.getKey() + "-" + entry.getValue().boss + ", ");
        }
        System.out.println();

        return uf.largestComponent;
    }

    static class UnionFind {
        Map<String, Node> map;
        int maxSize;
        List<String> largestComponent;

        UnionFind() {
            map = new HashMap<>();
            maxSize = 0;
            largestComponent = new LinkedList<>();
        }

        public void union(String item1, String item2) {
            if (!map.containsKey(item1)) {
                map.put(item1, new Node(item1));
            }
            if (!map.containsKey(item2)) {
                map.put(item2, new Node(item2));
            }
            if (isConnected(item1, item2) || item1.equals(item2)) return;

            Node boss1 = map.get(find(item1));
            Node boss2 = map.get(find(item2));

            // always choose lexicographically smaller item to be the boss of connected component
            if (boss1.component.get(0).compareTo(boss2.component.get(0)) < 0) {
                connect(boss1, boss2);
            } else {
                connect(boss2, boss1);
            }
        }

        public void connect(Node n1, Node n2) {
            n1.size += n2.size;
            n1.component.addAll(n2.component);
            n2.component = null;    // for saving space
            n2.boss = n1.item;

            if (n1.size > maxSize) {
                maxSize = n1.size;
                largestComponent = n1.component;
            } else if (n1.size == maxSize) {
                // if new component is same size but lexicographically smaller, update
                if (largestComponent.get(0).compareTo(n1.component.get(0)) > 0) {
                    largestComponent = n1.component;
                }
            }
        }

        public boolean isConnected(String s1, String s2) {
            String boss1 = find(s1);
            String boss2 = find(s2);
            return boss1.equals(boss2);
        }

        public String find(String item) {
            Node node = map.get(item);
            while (!node.boss.equals(node.item)) {
                node = map.get(node.boss);
            }
            Node tail = map.get(item);
            while (!tail.boss.equals(node.item)) {
                String next = tail.boss;
                tail.boss = node.item;
                tail = map.get(next);
            }
            return node.item;
        }
    }

    static class Node {
        String item;
        String boss;
        int size;
        LinkedList<String> component;

        Node(String s) {
            item = s;
            boss = s;
            size = 1;
            component = new LinkedList<>();
            component.add(s);
        }
    }
}
