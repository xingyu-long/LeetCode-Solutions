package com.leetcode.tree.SegementTree;

/**
 * @Date: 04/11/2020
 * @Description: Tree, Segement Tree
 **/
class STNode {

  int start;
  int end;
  int sum;
  STNode left;
  STNode right;

  public STNode(int s, int e, int sum, STNode l, STNode r) {
    start = s;
    end = e;
    this.sum = sum;
    left = l;
    right = r;
  }
}

public class ST {

  // T(n/2) = log(N)
  public void update(STNode root, int index, int val) {
    if (root.start == index && index == root.end) {
      root.sum = val;
      return;
    } else {
      int mid = root.start + (root.end - root.start) / 2;
      if (index <= mid) {
        update(root.left, index, val);
      } else {
        update(root.right, index, val);
      }
      root.sum = root.left.sum + root.right.sum;
    }
  }

  // 2T(n/2) + O(1) -> O(n)
  public STNode buildTree(int[] nums, int start, int end) {
    if (start > end) {
      return null;
    } else {
      if (start == end) {
        return new STNode(start, end, nums[start], null, null);
      }
      int mid = start + (end - start) / 2;
      STNode left = buildTree(nums, start, mid);
      STNode right = buildTree(nums, mid + 1, end);
      return new STNode(start, end, left.sum + right.sum, left, right);
    }
  }

  // logN
  public int querySum(STNode root, int i, int j) {
    if (root.start == i && root.end == j) {
      return root.sum;
    }
    int mid = root.start + (root.end - root.start) / 2;
    if (j <= mid) {
      return querySum(root.left, i, j);
    } else if (i > mid) {
      return querySum(root.right, i, j);
    } else { // 有交叉的情况
      return querySum(root.left, i, mid) +
          querySum(root.right, mid + 1, j);
    }
  }
}
