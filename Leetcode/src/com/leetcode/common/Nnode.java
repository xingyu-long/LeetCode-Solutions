package com.leetcode.common;

import java.util.List;

public class Nnode {
    public int val;
    public List<Nnode> children;

    public Nnode() {}

    public Nnode(int _val) {
        val = _val;
    }

    public Nnode(int _val, List<Nnode> _children) {
        val = _val;
        children = _children;
    }
};